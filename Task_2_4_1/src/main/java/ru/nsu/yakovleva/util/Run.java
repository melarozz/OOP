package ru.nsu.yakovleva.util;

import java.io.File;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

public class Run {

    @SneakyThrows
    public static boolean run(String labDir) {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(labDir)); //директория проекта
        @Cleanup ProjectConnection connection = connector.connect(); //подключаемся
        connection.newBuild()
                .forTasks("test", "javadoc")
                .run();
        return true;
    }
}

