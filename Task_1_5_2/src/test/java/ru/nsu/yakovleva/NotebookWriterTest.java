package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
public class NotebookWriterTest {
    private static final String TEST_FILE_NAME = "TestNotebook";

    @Test
    public void testFileCreationAndExistence() {
        NotebookWriter notebookWriter = new NotebookWriter(TEST_FILE_NAME);
        assertFalse(notebookWriter.exists());

        try {
            notebookWriter.open();
            assertTrue(notebookWriter.exists());
        } catch (IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testReadWriteOperations() {
        NotebookWriter notebookWriter = new NotebookWriter(TEST_FILE_NAME);

        try {
            notebookWriter.open();
            Note[] notesToWrite = {
                    new Note("Title 1", "Content 1"),
                    new Note("Title 2", "Content 2"),
            };

            notebookWriter.write(notesToWrite);

            Note[] readNotes = notebookWriter.read();

            assertNotNull(readNotes);
            assertEquals(notesToWrite.length, readNotes.length);


        } catch (IOException e) {
            fail("Exception not expected: " + e.getMessage());
        } finally {
            try {
                notebookWriter.close();
            } catch (IOException e) {
                fail("Exception while closing: " + e.getMessage());
            }
        }
    }
}
