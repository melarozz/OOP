package ru.nsu.yakovleva.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Reads JSON data from a file and converts it into Java objects.
 */
public class ReadJSON {

    // Default file name for JSON data
    private static final String DEFAULT_FILE_NAME = "pizzeria.json";

    // File object representing the JSON file
    private final File file;

    // File name
    private final String fileName;

    // Reader to read from the file
    private BufferedReader reader;

    /**
     * Constructs a new ReadJSON object with the default file name.
     */
    public ReadJSON() {
        this.fileName = DEFAULT_FILE_NAME;
        this.file = new File(DEFAULT_FILE_NAME);
    }

    /**
     * Constructs a new ReadJSON object with the specified file name.
     *
     * @param fileName The name of the file to read JSON data from.
     */
    public ReadJSON(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    /**
     * Retrieves the file name being read.
     *
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Opens the file for reading.
     */
    public void open() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reads all lines from the file and returns them as a single string.
     *
     * @param reader The reader to read from.
     * @return The content of the file as a string.
     * @throws IOException If an I/O error occurs.
     */
    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            content.append(currentLine).append("\n");
        }
        return content.toString();
    }

    /**
     * Reads JSON data from the file and converts it into a PizzeriaJSON object.
     *
     * @return The PizzeriaJSON object representing the JSON data read from the file.
     */
    public PizzeriaJSON read() {
        String content;
        try {
            content = readAllLines(reader);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }

        if (content.equals("")) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(content, PizzeriaJSON.class);
    }

    /**
     * Closes the reader.
     */
    public void close() {
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}