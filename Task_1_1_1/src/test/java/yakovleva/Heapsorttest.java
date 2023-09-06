package yakovleva;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


import static org.junit.jupiter.api.Assertions.assertEquals;

class Heapsorttest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
        originalIn = System.in;
    }

    @Test
    void testMainWithValidInput() {

        String input = "heapsort(new int[] {12, 11, 13, 5, 6, 7});\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(null);

        String expectedOutput = "Input: Output: [5, 6, 7, 11, 12, 13]\n";
        assertEquals(expectedOutput, output.toString());
    }

    @Test
    void check_usual() {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        int[] expected = { 5, 6, 7, 11, 12, 13};
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void testMainWithInvalidInput() {
        String input = "InvalidInput\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(null);

        String expectedOutput = "Input: Invalid in format.\n";
        assertEquals(expectedOutput, output.toString());
    }

    @Test
    void check_same() {
        int[] arr = { 12, 12, 12, 12, 12, 12 };
        int[] expected = { 12, 12, 12, 12, 12, 12 };
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void check_empty() {
        int[] arr = {};
        int[] expected = {};
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void check_sift() {
        int[] arr = {3, 1, 2};
        int[] expected = {3, 1, 2};

        Heapsort.sift(arr, arr.length, 0);
        assertArrayEquals(arr, expected);
    }

}