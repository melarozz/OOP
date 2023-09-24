package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class HeapSortTest {
    @Test
    void checkUsual() {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        int[] expected = { 5, 6, 7, 11, 12, 13};
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkSame() {
        int[] arr = { 12, 12, 12, 12, 12, 12 };
        int[] expected = { 12, 12, 12, 12, 12, 12 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkEmpty() {
        int[] arr = {};
        int[] expected = {};
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }


    @Test
    void checkLargeInput() {
        int[] arr = new int[10000000];
        int[] expected = new int[10000000];
        Arrays.fill(arr, 0);
        Arrays.fill(expected, 0);
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkSingleElement() {
        int[] arr = { 5 };
        int[] expected = { 5 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkNegativeNumbers() {
        int[] arr = { -5, -2, -8, -1, -3 };
        int[] expected = { -8, -5, -3, -2, -1 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }

    @Test
    void checkSortedArray() {
        int[] arr = { 1, 2, 3, 4, 5 };
        int[] expected = { 1, 2, 3, 4, 5 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }



}