package ru.nsu.yakovleva.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a method for searching for a
 * substring in a resource file using the Boyer-Moore algorithm.
 */
public class SubstringFinder {
    /**
     * Searches for the occurrences of a substring in a resource file
     * using the Boyer-Moore algorithm.
     *
     * @param resourceName The name of the resource file to search within.
     * @param substring    The substring to search for.
     * @return An array of integers representing the starting indexes
     *         of found substrings or [-1] if none are found.
     * @throws IOException If there is an error reading the resource file.
     */
    public static int[] boyerMooreSearch(
            String resourceName, String substring) throws IOException {
        InputStream inputStream = getResourceFileStream(resourceName);

        if (inputStream == null) {
            throw new IOException("Resource file not found: " + resourceName);
        }

        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        int m = substring.length();
        int bytesRead;
        int s = 0;
        List<Integer> foundIndexes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((bytesRead = reader.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead - m + 1; ) {
                    int j = m - 1;
                    while (j >= 0 && substring.charAt(j) == buffer[i + j]) {
                        j--;
                    }

                    if (j < 0) {
                        foundIndexes.add(s + i);
                        i += m;
                    } else {
                        int shift = j - substring.lastIndexOf(buffer[i + j]);
                        i += Math.max(1, shift);
                    }
                }
                s += bytesRead;
            }
        }

        if (foundIndexes.isEmpty()) {
            return new int[]{-1};
        } else {
            return foundIndexes.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    /**
     * Retrieves an InputStream for a resource file by its name.
     *
     * @param resourceName The name of the resource file.
     * @return An InputStream for the resource file or null if the resource is not found.
     */
    private static InputStream getResourceFileStream(String resourceName) {
        ClassLoader classLoader = SubstringFinder.class.getClassLoader();
        return classLoader.getResourceAsStream(resourceName);
    }
}
