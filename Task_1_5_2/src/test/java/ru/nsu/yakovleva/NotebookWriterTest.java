package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
public class NotebookWriterTest {
    private static final String testFileName = "TestNotebook";

    @Test
    public void testFileCreationAndExistence() throws IOException {
        NotebookWriter notebookWriter = new NotebookWriter(testFileName);
        try {
            notebookWriter.open();
            assertTrue(notebookWriter.exists());
        } catch (IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testReadWriteOperations() throws IOException {
        NotebookWriter notebookWriter = new NotebookWriter(testFileName);

        try {
            notebookWriter.open();
            Note[] notesToWrite = new Note[2];
            notesToWrite[0] = new Note("Title 1", "Content 1");
            notesToWrite[1] = new Note("Title 2", "Content 2");

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

    @Test
    public void testReadWithEmptyContent() {
        try {
            File tempFile = File.createTempFile("tempFile", ".json");
            NotebookWriter notebookWriter = new NotebookWriter(
                    tempFile.getName().replace(".json", ""));
            notebookWriter.open();
            Note[] notes = notebookWriter.read();
            assertNull(notes);
            notebookWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception thrown: " + e.getMessage());
        }
    }

}
