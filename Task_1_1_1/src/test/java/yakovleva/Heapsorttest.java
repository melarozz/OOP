package yakovleva;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {
    @Test
    void checkUsual() {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        int[] expected = { 5, 6, 7, 11, 12, 13};
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void checkSame() {
        int[] arr = { 12, 12, 12, 12, 12, 12 };
        int[] expected = { 12, 12, 12, 12, 12, 12 };
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void checkEmpty() {
        int[] arr = {};
        int[] expected = {};
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void checkSift() {
        int[] arr = {3, 1, 2};
        int[] expected = {3, 1, 2};

        Heapsort.sift(arr, arr.length, 0);
        assertArrayEquals(arr, expected);
    }

    @Test
    void checkLargeInput() {
        int[] arr = new int[1000000];
        int[] expected = new int[1000000];
        Arrays.fill(arr,0);
        Arrays.fill(expected,0);
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void checkSingleElement() {
        int[] arr = { 5 };
        int[] expected = { 5 };
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void checkNegativeNumbers() {
        int[] arr = { -5, -2, -8, -1, -3 };
        int[] expected = { -8, -5, -3, -2, -1 };
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }

    @Test
    void checkSortedArray() {
        int[] arr = { 1, 2, 3, 4, 5 };
        int[] expected = { 1, 2, 3, 4, 5 };
        int[] result = Heapsort.heapsort(arr);
        assertArrayEquals(result, expected);
    }



}