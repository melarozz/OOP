package ru.nsu.yakovleva.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.Data;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Data
public class TestsAndDocs {
    private int passedTests;
    private int totalTests;
    private String documentationExists;

    @SneakyThrows
    public void analyze(String testResPath, String documentationDir) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //фабрика для билдера документов
        DocumentBuilder builder = factory.newDocumentBuilder(); // получили из фабрики билдер, который парсит, создает структуру в виде иерархического дерева.
        Document junitDoc = builder.parse(new File(testResPath)); //парсинг результатов тестов
        Element junitTestSuite = (Element) junitDoc.getElementsByTagName("testsuite").item(0); // коллекция тестов, сгруппированных вместе и запускаемых как единая единица
        totalTests = Integer.parseInt(junitTestSuite.getAttribute("tests"));
        int failedTests = Integer.parseInt(junitTestSuite.getAttribute("failures"));
        passedTests = totalTests - failedTests;
        Path documentationPath = Paths.get(documentationDir);
        documentationExists = Files.exists(documentationPath) ? "Generated" : "Missing";
    }
}
