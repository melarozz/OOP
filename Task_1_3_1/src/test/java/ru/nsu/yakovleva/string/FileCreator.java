package ru.nsu.yakovleva.string;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The FileCreator class generates a large text file filled with random alphanumeric characters.
 */
public class FileCreator {

    /**
     * Generates a random alphanumeric string of a specified size.
     *
     * @param size The size of the alphanumeric string to generate
     * @return A random alphanumeric string of the given size
     */
    static String getAlphaNumericString(int size) {
        // Characters used to generate the alphanumeric string
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    /**
     * Creates a large text file filled with random alphanumeric strings.
     *
     * @param size The size of the file to be created in gigabytes
     */
    public static void createFile(int size) {
        String fileName = "large.txt";
        long sizeInBytes = size * 1024L * 1024L * 1024L;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder line = new StringBuilder();

            while (sizeInBytes > 0) {
                line.append(getAlphaNumericString(1024 * 1024));
                writer.write(line.toString());
                line.setLength(0);
                sizeInBytes -= 1024 * 1024;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Large file created: " + fileName);
    }
}
