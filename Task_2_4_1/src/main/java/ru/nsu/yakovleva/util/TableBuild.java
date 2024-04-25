package ru.nsu.yakovleva.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import ru.nsu.yakovleva.dsl.Group;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Cleanup;
import lombok.SneakyThrows;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

public class TableBuild {
    public static final String resultDir = "src/main/resources/results/";
    public static final String templatePath = "template.ftl";

    @SneakyThrows
    public static void generateHtmlTableChart(List<Group> groups) {
        Configuration configuration =
                new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setClassForTemplateLoading(TableBuild.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        File out = new File(resultDir, "output.html");
        @Cleanup Writer fileWriter = new FileWriter(out);  //с автозакрытием
        Template template = configuration.getTemplate(templatePath);
        Map<String, Object> dataModel = new HashMap<>(); //модель данных для шаблона
        dataModel.put("groups", groups);
        template.process(dataModel, fileWriter);
    }
}
