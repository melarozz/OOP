package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
public class AppExecuteTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testMainMethod() {
        String[] testArgs = {"-help"};

        AppExecute.main(testArgs);

        String consoleOutput = outputStreamCaptor.toString().trim();
        assertNotNull(consoleOutput);
    }

    @Test
    public void testMainMethodWithValidArgs() {
        String[] testArgs = {"-add", "test1", "test1"};

        AppExecute.main(testArgs);

        String consoleOutput = outputStreamCaptor.toString().trim();
        assertNotNull(consoleOutput);
    }

    @Test
    public void testMainMethodWithNullArgs() {
        AppExecute.main(null);

        String consoleOutput = outputStreamCaptor.toString().trim();
        assertNotNull(consoleOutput);
    }

    @Test
    public void testMainMethodWithEmptyArgs() {
        String[] testArgs = {};

        AppExecute.main(testArgs);

        String consoleOutput = outputStreamCaptor.toString().trim();
        assertNotNull(consoleOutput);
    }

    @Test
    public void testMainMethodWithInvalidArgs() {
        String[] testArgs = {"invalidArg"};

        AppExecute.main(testArgs);

        String consoleOutput = outputStreamCaptor.toString().trim();
        assertNotNull(consoleOutput);
    }

}
