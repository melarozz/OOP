package ru.nsu.yakovleva.heapsort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

class HeapSortTest {
    @Test
    void checkUsual() {
        int[] arr = { 12, 11, 13, 4, 6, 7 };
        int[] expected = { 4, 6, 7, 11, 12, 13};
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void checkSame() {
        int[] arr = { 12, 12, 12, 12, 12, 12 };
        int[] expected = { 12, 12, 12, 12, 12, 12 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void checkEmpty() {
        int[] arr = {};
        int[] expected = {};
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(expected, actual);
    }


    @Test
    void checkLargeInput() {
        Random random = new Random();
        int[] arr = new int[10000000];
        int[] expected = arr;
        Arrays.fill(arr, random.nextInt());
        Arrays.sort(expected);
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(actual, expected);
    }


    @Test
    void checkSingleElement() {
        int[] arr = { 5 };
        int[] expected = { 5 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void checkNegativeNumbers() {
        int[] arr = { -5, -2, -8, -1, -3 };
        int[] expected = { -8, -5, -3, -2, -1 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void checkSortedArray() {
        int[] arr = { 1, 2, 3, 4, 5 };
        int[] expected = { 1, 2, 3, 4, 5 };
        int[] actual = Heapsort.heapsort(arr);
        assertArrayEquals(expected, actual);
    }

}