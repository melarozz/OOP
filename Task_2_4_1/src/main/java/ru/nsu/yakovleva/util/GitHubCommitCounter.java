package ru.nsu.yakovleva.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GitHubCommitCounter {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/";
    private static final String AUTH_TOKEN = "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";

    public static int count(String owner, String repo, LocalDate sinceDate, LocalDate untilDate) {
        try {
            String since = sinceDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            String until = untilDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

            URL url = new URL(GITHUB_API_URL + owner + "/" + repo + "/commits?since=" + since + "T00:00:00Z&until=" + until + "T23:59:59Z");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "token " + AUTH_TOKEN);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();

                return response.toString().split("\\{\"sha\":\"").length - 1;
            } else {
                System.out.println("Error retrieving commits. Response code: " + responseCode);
                return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
