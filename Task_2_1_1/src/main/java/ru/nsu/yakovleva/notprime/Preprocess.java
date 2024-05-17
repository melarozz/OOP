package ru.nsu.yakovleva.notprime;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Data preprocessor class.
 */
public final class Preprocess {
    /**
     * Method that separates array into pieces.
     *
     * @param array  - array
     * @param chunks - number of pieces
     * @return queue with strings
     */
    public static BlockingQueue<String> arraySeparator(int[] array, int chunks) throws InterruptedException {
        int subArrayLength = array.length / chunks;
        int remainder = array.length % chunks;
        BlockingQueue<String> result = new ArrayBlockingQueue<>(chunks);

        int start = 0;
        for (int i = 0; i < chunks; i++) {
            int end = start + subArrayLength + (i < remainder ? 1 : 0);
            int[] subArray = Arrays.copyOfRange(array, start, end);
            result.put(makeString(subArray));
            start = end;
        }

        return result;
    }


    /**
     * Method that makes string from array.
     *
     * @param array - array
     * @return string
     */
    public static String makeString(int[] array) {
        return Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }


    /**
     * Method that makes array from string.
     *
     * @param value - string
     * @return array
     */
    public static int[] makeArray(String value) {
        return Arrays.stream(value.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}