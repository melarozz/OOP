package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void getNotesByKeywords_throwsNullPointerException() {
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
}