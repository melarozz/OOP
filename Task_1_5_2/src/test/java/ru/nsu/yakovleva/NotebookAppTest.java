package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
class NotebookAppTest {
    @Test
    public void testHelpMethod() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        NotebookApp app = new NotebookApp();
        app.help();

        String helpOutput = outputStream.toString();

        assertFalse(helpOutput.isEmpty());

        System.setOut(System.out);
    }

}