package ru.nsu.yakovleva.notprime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Test class for stream search.
 */
class StreamNotPrimeSearchTest {
    @Test
    public void throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new StreamNotPrimeSearch().search(null));
    }

    @Test
    public void smallData() {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 29);
        assertFalse(new StreamNotPrimeSearch().search(array));
    }

    @Test
    public void anotherSmallData() {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 17);
        array[size - 1] = 512;
        assertTrue(new StreamNotPrimeSearch().search(array));
    }

    @Test
    public void largeData() {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 2097143);
        assertFalse(new StreamNotPrimeSearch().search(array));
    }

    @Test
    public void anotherLargeData() {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 100003);
        array[size - 1] = 77;
        assertTrue(new StreamNotPrimeSearch().search(array));
    }
}
