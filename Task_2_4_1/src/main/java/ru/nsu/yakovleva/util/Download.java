package ru.nsu.yakovleva.util;

import lombok.SneakyThrows;

import java.io.IOException;

public class Download {
    public static final String labs = "src/main/resources/labs/";

    @SneakyThrows
    public static boolean download(String repo, String folder, String branch) {
        //process builder doesn't have to be closed anyhow
        ProcessBuilder processBuilder = new ProcessBuilder("git",
                "clone", "-b", branch, repo, labs + folder);
        Process process = processBuilder.start();
        int exitCode = process.waitFor(); //ждем завершения
        return exitCode == 0;
    }
}
