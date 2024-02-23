package ru.nsu.yakovleva.notprime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for regular search.
 */
class NotPrimeSearchTest {
    @Test
    public void negativeNumber() {
        Assertions.assertFalse(new NotPrimeSearch().isPrime(-5));
    }

    @Test
    public void smallNumber() {
        assertTrue(new NotPrimeSearch().isPrime(7));
    }

    @Test
    public void evenNumber() {
        assertFalse(new NotPrimeSearch().isPrime(44));
    }

    @Test
    public void largeNotPrimeNumber() {
        assertFalse(new NotPrimeSearch().isPrime(123456789));
    }

    @Test
    public void largePrimeNumber() {
        assertTrue(new NotPrimeSearch().isPrime(9999991));
    }

    @Test
    public void throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NotPrimeSearch().search(null));
    }

    @Test
    public void smallData() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 23);
        assertFalse(new NotPrimeSearch().search(array));
    }

    @Test
    public void anotherSmallData() throws ExecutionException, InterruptedException {
        int size = 100;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 19);
        array[size - 1] = 200;
        assertTrue(new NotPrimeSearch().search(array));
    }

    @Test
    public void largeData() throws ExecutionException, InterruptedException {
        int size = 1000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size, 3571);
        assertFalse(new NotPrimeSearch().search(array));
    }

    @Test
    public void anotherLargeData() throws ExecutionException, InterruptedException {
        int size = 1000000;
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 68921);
        array[size - 1] = 987654321;
        assertTrue(new NotPrimeSearch().search(array));
    }
}
