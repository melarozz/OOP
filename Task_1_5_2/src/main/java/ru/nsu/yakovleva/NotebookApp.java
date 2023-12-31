package ru.nsu.yakovleva;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * NotebookApplication is a class for parsing command line
 * and performing the specified operations on the notebook.
 * Uses JSON files for storing data.
 */
public class NotebookApp {
    final Options options;
    NotebookWriter writer;

    /**
     * Creates an instance of the class NotebookApplication
     * and describes operations for working with a notebook.
     */
    public NotebookApp() {
        options = new Options();
        Option help = new Option("help", "print Notebook App help information");
        Option title = Option
                .builder("title")
                .hasArg().optionalArg(false)
                .argName("notebook")
                .build();
        Option add = Option
                .builder("add")
                .numberOfArgs(2)
                .optionalArg(false)
                .argName("title> <content")
                .build();
        Option remove = Option
                .builder("remove")
                .hasArg()
                .optionalArg(false)
                .argName("title")
                .build();
        Option show = Option
                .builder("show")
                .hasArgs()
                .optionalArg(true)
                .argName("before> <after> <keywords")
                .build();

        options.addOption(help);
        options.addOption(title);
        options.addOption(add);
        options.addOption(remove);
        options.addOption(show);
    }

    /**
     * Displays a description of all the operations available in Notebook Application.
     */
    void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("notebook.jar", options);
    }

    /**
     * Saves specified note in JSON file associated with a notebook.
     *
     * @param note - the note to save
     * @throws IOException if there are problems when working with the file.
     */
    void add(Note note) throws IOException {
        Notebook notebook = new Notebook(writer.getFileName());
        notebook.addNote(note);
        notebook.addNotes(writer.read());
        writer.write(notebook.getAllNotes());
    }

    /**
     * Removes note by title from a JSON file associated with a notebook.
     *
     * @param title - the name of the note to be removed.
     * @throws IOException if there are problems when working with the file.
     */
    void remove(String title) throws IOException {
        Notebook notebook = new Notebook(writer.getFileName());
        notebook.addNotes(writer.read());
        notebook.removeNote(title);
        writer.write(notebook.getAllNotes());
    }

    /**
     * Displays all notes from a JSON file associated with a notebook.
     *
     * @throws IOException if there are problems when working with the file.
     */
    void show() throws IOException {
        Notebook notebook = new Notebook(writer.getFileName());
        Note[] notes = writer.read();
        if (notes == null || notes.length == 0) {
            System.out.println("Could not find notes.");
            return;
        }
        notebook.addNotes(notes);
        System.out.println(notebook);
    }

    /**
     * Displays all notes, created in specified time range, from a JSON file
     * associated with a notebook.
     *
     * @param date1 - the beginning of the time range.
     * @param date2 - the end of the time range.
     * @throws IOException              if there are problems when working with the file.
     * @throws java.text.ParseException if the date was specified in the wrong format.
     */
    void show(String date1, String date2) throws IOException, java.text.ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date before = formatter.parse(date1);
        Date after = formatter.parse(date2);

        Notebook notebook = new Notebook(writer.getFileName());
        notebook.addNotes(writer.read());

        Note[] notes = notebook.getNotesByDate(before, after);
        if (notes == null || notes.length == 0) {
            System.out.println("No notes found within the specified dates.");
            return;
        }

        Notebook filteredNotebook = new Notebook(writer.getFileName());
        filteredNotebook.addNotes(notes);
        System.out.println(filteredNotebook);
    }

    /**
     * Displays all notes by date and keywords from a JSON file associated with a notebook.
     *
     * @param date1    - the beginning of the time range.
     * @param date2    - the end of the time range.
     * @param keywords - words that should be included in the titles of the searched notes.
     * @throws IOException              if there are problems when working with the file.
     * @throws java.text.ParseException if the date was specified in the wrong format.
     */
    void show(String date1, String date2, String[] keywords)
            throws IOException, java.text.ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date before = formatter.parse(date1);
        Date after = formatter.parse(date2);

        Notebook notebook = new Notebook(writer.getFileName());
        notebook.addNotes(writer.read());

        Note[] notes = notebook.getNotesByDateAndKeywords(before, after, keywords);
        if (notes == null || notes.length == 0) {
            System.out.println("No matching notes found.");
            return;
        }

        Notebook filteredNotebook = new Notebook(writer.getFileName());
        filteredNotebook.addNotes(notes);
        System.out.println(filteredNotebook);
    }

    /**
     * Parses command line and performs specified operations on the notebook.
     *
     * @param line - command line to parse.
     * @throws IOException              if there are problems when working with the file.
     * @throws java.text.ParseException if one of the arguments of an
     *                                  operation is in the wrong format.
     */
    void parseLine(CommandLine line) throws IOException, java.text.ParseException {
        if (line.hasOption("help")) {
            help();
            return;
        }

        String title = line.getOptionValue("title");
        writer = (title != null) ? new NotebookWriter(title) : new NotebookWriter();
        writer.open();

        if (line.hasOption("add")) {
            String[] values = line.getOptionValues("add");
            Optional.ofNullable(values)
                    .filter(arr -> arr.length >= 2)
                    .ifPresent(arr -> {
                        try {
                            add(new Note(arr[0], arr[1]));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        if (line.hasOption("remove")) {
            remove(line.getOptionValue("remove"));
        }

        if (line.hasOption("show")) {
            String[] values = line.getOptionValues("show");
            if (values == null || values.length == 0) {
                show();
            } else if (values.length >= 2) {
                if (values.length == 2) {
                    show(values[0], values[1]);
                } else {
                    show(values[0], values[1], Arrays.copyOfRange(values, 2, values.length));
                }
            }
        }

        writer.close();
    }

    /**
     * Starts the Notebook Application and presents the arguments for
     * parsing in an appropriate format, then passes them
     * to parseLine method.
     *
     * @param args - arguments from command line.
     */
    public void run(String[] args) {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(options, args);
            parseLine(line);
        } catch (Exception exception) {
            help();
        }
    }
}
