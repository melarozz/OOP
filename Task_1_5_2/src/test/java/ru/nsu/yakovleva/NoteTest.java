package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
class NoteTest {
    @Test
    public void testNoteCreation() {
        String title = "Test Title";
        String content = "Test Content";
        Note note = new Note(title, content);

        assertEquals(title, note.getTitle());
        assertEquals(content, note.getContent());
        assertNotNull(note.getTimestamp());
    }

    @Test
    public void testNoteEquality() {
        String title = "Title";
        String content = "Content";
        Note note1 = new Note(title, content);
        Note note2 = new Note(title, content);

        assertEquals(note1, note2);
    }

    @Test
    public void testNoteInequality() {
        String title1 = "Title 1";
        String title2 = "Title 2";
        String content = "Content";
        Note note1 = new Note(title1, content);
        Note note2 = new Note(title2, content);

        assertNotEquals(note1, note2);
    }

    @Test
    public void testEqualsDifferentTypes() {
        Note note = new Note("Title 5", "Content 5");
        Integer integer = 10;
        assertNotEquals(note, integer);
    }
}