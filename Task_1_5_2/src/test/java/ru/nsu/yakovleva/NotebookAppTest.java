package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.text.ParseException;

/**
 * Test class.
 */
class NotebookAppTest {

    @Mock
    private NotebookWriter writer;

    @InjectMocks
    private NotebookApp notebookApp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCorrectlyAddsNote() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{});
        doNothing().when(writer).write(any());

        Note note = new Note("Test Title", "Test Content");
        assertDoesNotThrow(() -> notebookApp.add(note));
    }

    @Test
    void removeCorrectlyRemovesNote() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{new Note("Title", "Content")});
        doNothing().when(writer).write(any());

        assertDoesNotThrow(() -> notebookApp.remove("Title"));
    }

    @Test
    void showCorrectlyShowsNotes() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{new Note("Title", "Content")});

        assertDoesNotThrow(() -> notebookApp.show());
    }

    @Test
    void showWithDatesAndKeywordsCorrectlyShowsFilteredNotes() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{
                new Note("Title 1", "Content with keyword"),
                new Note("Title 2", "Content without keyword")
        });

        assertDoesNotThrow(() -> notebookApp.show("2023.01.01 00:00",
                "2024.01.01 00:00", new String[]{"keyword"}));
    }

    @Test
    void helpCommandDisplaysHelpInformation() {
        String[] args = {"-help"};
        assertDoesNotThrow(() -> notebookApp.run(args));
    }

    @Test
    void performMultipleOperations() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{});

        String[] args = {"-add", "Title1", "Content1", "-remove", "Title1", "-show"};
        assertDoesNotThrow(() -> notebookApp.run(args));
    }

    @Test
    void showAllNotes() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{
                new Note("Title 1", "Content 1"),
                new Note("Title 2", "Content 2")
        });

        assertDoesNotThrow(() -> notebookApp.show());
    }

    @Test
    void showNotesByDateRange() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{
                new Note("Title 1", "Content 1"),
                new Note("Title 2", "Content 2")
        });

        assertDoesNotThrow(() -> notebookApp.show("2023.01.01 00:00", "2024.01.01 00:00"));
    }

    @Test
    void showNotesByDateAndKeywords() throws IOException {
        when(writer.getFileName()).thenReturn("testFile.json");
        doNothing().when(writer).open();
        doNothing().when(writer).close();
        when(writer.read()).thenReturn(new Note[]{
                new Note("Title 1", "Content with keyword"),
                new Note("Title 2", "Content without keyword")
        });

        assertDoesNotThrow(() -> notebookApp.show("2023.01.01 00:00",
                "2024.01.01 00:00", new String[]{"Title"}));
    }

}
