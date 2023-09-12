package yakovleva;

import org.junit.jupiter.api.Test;
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

}