package ru.nsu.yakovleva.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
        // Open an InputStream for the resource file
        InputStream inputStream = getResourceFileStream(resourceName);

        // Check if the resource file was found, throw an exception if not
        if (inputStream == null) {
            throw new IOException("Resource file not found: " + resourceName);
        }

        // Set the buffer size for reading data from the file
        int bufferSize = 1024;
        // Create a character buffer to read data
        char[] buffer = new char[bufferSize];
        // Length of the substring to search for
        int m = substring.length();
        // Number of characters read
        int bytesRead;
        // Variable to keep track of the current position in the resource file
        int s = 0;
        // List to store the starting indexes of found substrings
        List<Integer> foundIndexes = new ArrayList<>();
        int countIdx = 0;

        File file = new File("found_indexes.txt");
        FileWriter outputFile = null;

        // Use try-with-resources to ensure the BufferedReader is properly closed
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream, StandardCharsets.UTF_8))) {
            // Read data from the resource file into the buffer

            while ((bytesRead = reader.read(buffer)) != -1) {
                // Iterate through the characters in the buffer to search for the substring
                for (int i = 0; i < bytesRead - m + 1; ) {
                    int j = m - 1;
                    // Compare characters from the end of the substring
                    while (j >= 0 && substring.charAt(j) == buffer[i + j]) {
                        j--;
                    }


                    // If the entire substring is matched, add the starting index to the list
                    if (j < 0) {
                        if (countIdx < 10000) {
                            foundIndexes.add(s + i);
                        } else if (countIdx == 10000) {
                            if (file.exists()) {
                                file.delete(); // Delete the file if it exists
                            }
                            outputFile = new FileWriter(file);
                            writeIndexesToFile(outputFile, foundIndexes);
                            foundIndexes.clear();
                        } else {
                            assert outputFile != null;
                            outputFile.write(Integer.toString(s + i));
                            outputFile.write("\n");
                        }
                        countIdx++;
                        i += m;
                    } else {
                        // Calculate the shift based on the last character in the substring
                        int shift = j - substring.lastIndexOf(buffer[i + j]);
                        // Move the index according to the maximum of 1 and the calculated shift
                        i += Math.max(1, shift);
                    }
                }
                // Update the position in the resource file
                s += bytesRead;
            }
        }

        return foundIndexes.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Retrieves an InputStream for a resource file by its name.
     *
     * @param resourceName The name of the resource file.
     * @return An InputStream for the resource file or null if the resource is not found.
     */
    private static InputStream getResourceFileStream(String resourceName) {
        // Get the ClassLoader for the SubstringFinder class
        ClassLoader classLoader = SubstringFinder.class.getClassLoader();
        // Get an InputStream for the resource file using the ClassLoader
        return classLoader.getResourceAsStream(resourceName);
    }

    /**
     * Writes found indexes to file if neccessary.
     *
     * @param outputFile output file.
     * @param indexes found indexes.
     * @throws IOException if file gives error.
     */
    private static void writeIndexesToFile(
            FileWriter outputFile, List<Integer> indexes) throws IOException {
        for (int index : indexes) {
            outputFile.write(Integer.toString(index));
            outputFile.write("\n");
        }
    }
}
