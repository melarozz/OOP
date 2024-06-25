package ru.nsu.yakovleva.notprime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

/**
 * Test class for thread search.
 */
class ThreadNotPrimeSearchTest {

    @Test
    public void throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new ThreadNotPrimeSearch(4).search(null));
    }

    @Test
    public void smallData() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 23);
        assertFalse(new ThreadNotPrimeSearch(4).search(array));
    }

    @Test
    public void anotherSmallData() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 19);
        array[size - 1] = 333;
        assertTrue(new ThreadNotPrimeSearch(4).search(array));
    }

    @Test
    public void largeData() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 999983);
        assertFalse(new ThreadNotPrimeSearch(4).search(array));
    }

    @Test
    public void anotherLargeData() throws ExecutionException, InterruptedException {
        int size = 10000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 524287);
        array[size - 1] = 555;
        assertTrue(new ThreadNotPrimeSearch(4).search(array));
    }
}
