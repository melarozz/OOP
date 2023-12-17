package ru.nsu.yakovleva;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for a note with the title, content and its timestamp.
 */
public class Note {
    private final String title;
    private final String content;
    private final Date timestamp;

    /**
     * Creates an instance of the class Note.
     *
     * @param title   - the title of the note.
     * @param content - the content of the note.
     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.timestamp = new Date();
    }

    /**
     * Getting the title of the note.
     *
     * @return the title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getting the content of the note.
     *
     * @return the content of the note.
     */
    public String getContent() {
        return content;
    }

    /**
     * Getting the note's creation date.
     *
     * @return the note's creation date.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Note as a string.
     *
     * @return the note as a string.
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String date = formatter.format(timestamp);
        return "Title: \""
                + title
                + "\"\n"
                + "Date of creation: "
                + date
                + "\n"
                + "Note: \""
                + content
                + "\"";
    }

    /**
     * Compares this note with the specified object.
     *
     * @param object - the object to compare with.
     * @return true if the objects are the same.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Note)) {
            return false;
        }

        Note note = (Note) object;

        String title = note.getTitle();
        String content = note.getContent();
        Date timestamp = note.getTimestamp();
        return this.title.equals(title) && this.content.equals(content)
                && this.timestamp.equals(timestamp);
    }
}
