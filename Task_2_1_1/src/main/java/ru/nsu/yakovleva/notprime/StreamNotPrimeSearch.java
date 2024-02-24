package ru.nsu.yakovleva.notprime;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

/**
 * The StreamNotPrimeSearch class extends the functionality of NotPrimeSearch
 * by providing a parallel stream-based implementation
 * for searching non-prime numbers in an array.
 */
public class StreamNotPrimeSearch extends NotPrimeSearch {

    /**
     * Searches for non-prime numbers in the given array using parallel streams.
     *
     * @param array The array of integers to be searched.
     * @return true if there is a non-prime number in the array, false otherwise.
     * @throws NullPointerException If the input array is null.
     */
    @Override
    public boolean search(int @NotNull [] array) throws NullPointerException {

        return Arrays.stream(array)
                .parallel()
                .anyMatch(number -> !isPrime(number));
    }
}