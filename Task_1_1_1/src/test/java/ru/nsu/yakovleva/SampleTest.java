package ru.nsu.yakovleva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {

    @Test
    void check_usual() {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        int[] expected = { 5, 6, 7, 11, 12, 13};
        int[] result = Sample.sort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void check_same() {
        int[] arr = { 12, 12, 12, 12, 12, 12 };
        int[] expected = { 12, 12, 12, 12, 12, 12 };
        int[] result = Sample.sort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void check_empty() {
        int[] arr = {};
        int[] expected = {};
        int[] result = Sample.sort(arr);
        assertArrayEquals(result, expected);
    }

}
