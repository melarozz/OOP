package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
class NotebookTest {
    private Notebook notebook;

    @BeforeEach
    public void initialize() {
        notebook = new Notebook("Test");
    }

    @Test
    public void testAddNote() {
        Note note = new Note("Test Title", "Test Content");
        notebook.addNote(note);

        assertEquals(1, notebook.getAllNotes().length);
        assertEquals(note, notebook.getAllNotes()[0]);
    }

    @Test
    public void testAddNoteStrings() {
        Note note = new Note("Test2 Title", "Test Content");
        notebook.addNote("Test2 Title", "Test Content");

        assertEquals(1, notebook.getAllNotes().length);
        assertEquals(note, notebook.getAllNotes()[0]);
    }

    @Test
    public void addNotes() {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 2", "Content 2");
        expected[1] = new Note("Title 3", "Content 3");
        notebook.addNotes(expected);

        Note[] actual = notebook.getAllNotes();
        assertArrayEquals(actual, expected);
    }

    @Test
    public void testRemoveNote() {
        Note note = new Note("Test Title", "Test Content");
        notebook.addNote(note);

        notebook.removeNote("Test Title");

        assertEquals(0, notebook.getAllNotes().length);
    }

    @Test
    public void getTitle() {
        assertEquals("Test", notebook.getTitle());
    }

    @Test
    public void getAllNotes() {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 5", "Content 5");
        expected[1] = new Note("Title 6", "Content 6");
        notebook.addNotes(expected);
        notebook.addNote("Title 7", "Content 7");
        notebook.addNote("Title 8", "Content 8");
        notebook.removeNote("Title 7");
        notebook.removeNote("Title 8");

        Note[] actual = notebook.getAllNotes();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getNotesByKeywordsThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notebook.getNotesByKeywords(null));
    }

    @Test
    public void getNotesByKeywords() {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 9", "Content 9");
        expected[1] = new Note("Title 10", "Content 10");
        notebook.addNotes(expected);
        notebook.addNote("Undefined", "Undefined");

        Note[] actual = notebook.getNotesByKeywords(new String[]{"Title"});
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getNotesByDate() {
        Note[] expected = new Note[3];
        expected[0] = new Note("Title 11", "Content 11");
        expected[1] = new Note("Title 12", "Content 12");
        expected[2] = new Note("Title 13", "Content 13");
        notebook.addNotes(expected);

        Note[] actual = notebook.getNotesByDate(expected[0].getTimestamp());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetNotesByDate() throws InterruptedException {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 14", "Content 14");
        expected[1] = new Note("Title 15", "Content 15");
        notebook.addNotes(expected);
        Thread.sleep(1000);
        notebook.addNote("Title 16", "Content 16");
        Date before = expected[0].getTimestamp();
        Date after = expected[1].getTimestamp();
        Note[] actual = notebook.getNotesByDate(before, after);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getNotesByDateAndKeywords() throws InterruptedException {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 17", "Content 17");
        expected[1] = new Note("Title 18", "Content 18");
        notebook.addNotes(expected);
        notebook.addNote("Undefined", "Undefined");
        Thread.sleep(1000);
        notebook.addNote("Title 19", "Content 19");
        Date before = expected[0].getTimestamp();
        Date after = expected[1].getTimestamp();
        Note[] actual = notebook.getNotesByDateAndKeywords(before, after, new String[]{"Title"});
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAddNullNote() {
        assertThrows(NullPointerException.class, () -> notebook.addNote((Note) null));
    }

    @Test
    public void testRemoveNonExistentNote() {
        Note note = new Note("Non-existent", "Note");
        notebook.addNote(note);
        notebook.removeNote("Non-existent Note");

        assertEquals(1, notebook.getAllNotes().length);
    }

    @Test
    public void getNotesByKeywordsNoMatches() {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 20", "Content 20");
        expected[1] = new Note("Title 21", "Content 21");
        notebook.addNotes(expected);

        Note[] actual = notebook.getNotesByKeywords(new String[]{"Random"});
        assertEquals(0, actual.length);
    }

    @Test
    public void getNotesByDateRangeNoMatches() {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 22", "Content 22");
        expected[1] = new Note("Title 23", "Content 23");
        notebook.addNotes(expected);

        Date after = new Date(System.currentTimeMillis() + 100000); // Future date
        Date before = new Date(System.currentTimeMillis() + 200000); // Another future date

        Note[] actual = notebook.getNotesByDate(after, before);
        assertEquals(0, actual.length);
    }

    @Test
    public void testPerformance() {
        final int NUM_NOTES = 10000;
        Note[] expected = new Note[NUM_NOTES];

        // Add a large number of notes
        for (int i = 0; i < NUM_NOTES; i++) {
            expected[i] = new Note("Title " + i, "Content " + i);
        }
        notebook.addNotes(expected);

        long start = System.nanoTime();
        Note[] allNotes = notebook.getAllNotes();
        long elapsedTime = System.nanoTime() - start;
        assertTrue(elapsedTime < 100000000);
        assertArrayEquals(expected, allNotes);

        String[] keywords = {"Title"};
        start = System.nanoTime();
        Note[] notesByKeywords = notebook.getNotesByKeywords(keywords);
        elapsedTime = System.nanoTime() - start;
        assertTrue(elapsedTime < 100000000);
        for (Note note : notesByKeywords) {
            assertTrue(note.getTitle().contains("Title"));
        }

        Date after = new Date(System.currentTimeMillis() - 100000); // Past date
        start = System.nanoTime();
        Note[] notesByDate = notebook.getNotesByDate(after);
        elapsedTime = System.nanoTime() - start;
        assertTrue(elapsedTime < 100000000);
        for (Note note : notesByDate) {
            assertTrue(note.getTimestamp().after(after) || note.getTimestamp().equals(after));
        }

        Date before = new Date(); // Current date
        start = System.nanoTime();
        Note[] notesByDateRange = notebook.getNotesByDate(after, before);
        elapsedTime = System.nanoTime() - start;
        assertTrue(elapsedTime < 100000000);
        for (Note note : notesByDateRange) {
            assertTrue(note.getTimestamp().after(after) || note.getTimestamp().equals(after));
            assertTrue(note.getTimestamp().before(before) || note.getTimestamp().equals(before));
        }

        start = System.nanoTime();
        Note[] notesByDateAndKeywords = notebook.getNotesByDateAndKeywords(after, before, keywords);
        elapsedTime = System.nanoTime() - start;
        assertTrue(elapsedTime < 100000000);
        for (Note note : notesByDateAndKeywords) {
            assertTrue(note.getTitle().contains("Title"));
            assertTrue(note.getTimestamp().after(after) || note.getTimestamp().equals(after));
            assertTrue(note.getTimestamp().before(before) || note.getTimestamp().equals(before));
        }
    }

    @Test
    public void testRemoveMultipleNotesWithSameTitle() {
        Note[] expected = new Note[3];
        expected[0] = new Note("Title 24", "Content 24");
        expected[1] = new Note("Title 24", "Content 25");
        expected[2] = new Note("Title 25", "Content 26");
        notebook.addNotes(expected);

        notebook.removeNote("Title 24");

        assertEquals(1, notebook.getAllNotes().length);
    }

    @Test
    public void testToString() {
        Note[] expected = new Note[2];
        expected[0] = new Note("Title 38", "Content 38");
        expected[1] = new Note("Title 39", "Content 39");
        notebook.addNotes(expected);

        StringBuilder expectedString = new StringBuilder("---Test---\n");
        for (Note note : expected) {
            expectedString.append(note.toString()).append("\n------\n");
        }

        String actualString = notebook.toString();
        assertEquals(expectedString.toString(), actualString);
    }

}