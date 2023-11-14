package ru.nsu.yakovleva.string;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Test class.
 */
public class SubstringFinderTest {

    @Test
    public void testOnePatternFound() throws IOException {
        int[] indexes = SubstringFinder.boyerMooreSearch(
                "input.txt", "pattern");
        Assertions.assertArrayEquals(new int[]{14}, indexes);
    }

    @Test
    public void testTwoIndexesFound() throws IOException {
        int[] indexes = SubstringFinder.boyerMooreSearch(
                "input.txt", "test");
        Assertions.assertArrayEquals(new int[]{22, 35}, indexes);
    }

    @Test
    public void testEmptyFile() throws IOException {
        int[] indexes = SubstringFinder.boyerMooreSearch(
                "emptyfile.txt", "pattern");
        Assertions.assertArrayEquals(new int[]{-1}, indexes);
    }

    @Test
    public void testSearchInLargeFile10Mb() throws IOException {
        int[] indexes = SubstringFinder.boyerMooreSearch(
                "large-file.txt", "text");
        Assertions.assertNotEquals(-1, indexes[0]);
    }

    @Test
    public void testNonExistingPattern() throws IOException {
        int[] indexes = SubstringFinder.boyerMooreSearch(
                "input.txt", "nonexistent");
        Assertions.assertArrayEquals(new int[]{-1}, indexes);
    }

    //字符串
    @Test
    public void testChinese() throws IOException {
        int[] indexes = SubstringFinder.boyerMooreSearch(
                "chinese.txt", "字符串");
        Assertions.assertArrayEquals(new int[]{1, 24}, indexes);
    }

    @Test
    public void testResourceFileNotFound() {
        Assertions.assertThrows(IOException.class, () -> {
            SubstringFinder.boyerMooreSearch(
                    "nonexistent.txt", "pattern");
        });
    }
}
