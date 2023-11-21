package ru.nsu.yakovleva.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubstringFinder {

    public static int[] boyerMooreSearch(String filePath, String substring) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        int m = substring.length();
        int bytesRead;
        int s = 0;
        List<Integer> foundIndexes = new ArrayList<>();
        int countIdx = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            while ((bytesRead = reader.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead - m + 1; ) {
                    int j = m - 1;
                    while (j >= 0 && substring.charAt(j) == buffer[i + j]) {
                        j--;
                    }

                    if (j < 0) {
                        if (countIdx < 10000) {
                            foundIndexes.add(s + i);
                        } else if (countIdx == 10000) {
                            outputFile(foundIndexes);
                            foundIndexes.clear();
                        } else {
                            try (FileWriter outputFile = new FileWriter("found_indexes.txt", true)) {
                                outputFile.write(Integer.toString(s + i));
                                outputFile.write("\n");
                            }
                        }
                        countIdx++;
                        i += m;
                    } else {
                        int shift = j - substring.lastIndexOf(buffer[i + j]);
                        i += Math.max(1, shift);
                    }
                }
                s += bytesRead;
            }
        }

        return foundIndexes.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void outputFile(List<Integer> indexes) throws IOException {
        try (FileWriter outputFile = new FileWriter("found_indexes.txt")) {
            for (int index : indexes) {
                outputFile.write(Integer.toString(index));
                outputFile.write("\n");
            }
        }
    }
}
