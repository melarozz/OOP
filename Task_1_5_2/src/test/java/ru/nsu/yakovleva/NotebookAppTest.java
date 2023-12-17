package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
public class NotebookAppTest {
    private NotebookApp notebookApp;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Initializing.
     */
    @BeforeEach
    public void setUp() {
        notebookApp = new NotebookApp();
        System.setOut(new PrintStream(outContent));
        notebookApp.writer = new NotebookWriter();
    }

    @Test
    public void testHelp() {
        notebookApp.help();
        String expectedOutput = "usage: notebook.jar\n"
                + " -add <title> <content>\n"
                + " -help                               print Notebook App help information\n"
                + " -remove <title>\n"
                + " -show <before> <after> <keywords>\n"
                + " -title <notebook>";
        assertEquals(expectedOutput, outContent.toString().trim());
    }


    @Test
    public void testParseLineHelpOption() throws IOException,
            java.text.ParseException, org.apache.commons.cli.ParseException {
        String[] args = {"-help"};
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(notebookApp.options, args);
        notebookApp.parseLine(line);

        String expectedOutput = "usage: notebook.jar\n"
                + " -add <title> <content>\n"
                + " -help                               print Notebook App help information\n"
                + " -remove <title>\n"
                + " -show <before> <after> <keywords>\n"
                + " -title <notebook>";

        assertEquals(expectedOutput, outContent.toString().trim());
    }


    @Test
    public void testRunWithInvalidArguments() {
        String[] args = {"invalid", "arguments"};
        notebookApp.run(args);
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
