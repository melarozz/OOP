package ru.nsu.yakovleva.notprime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;

/**
 * ThreadNotPrimeSearch extends NotPrimeSearch and uses multiple threads
 * to perform a parallel search for non-prime numbers in an integer array.
 */
public class ThreadNotPrimeSearch extends NotPrimeSearch {
    private final int numThreads;

    /**
     * Constructs a ThreadNotPrimeSearch with the specified number of threads.
     *
     * @param numThreads The number of threads to be used for parallel processing.
     * @throws IllegalArgumentException if the number of threads is less than or equal to 0.
     */
    public ThreadNotPrimeSearch(int numThreads) {
        if (numThreads <= 0) {
            throw new IllegalArgumentException("Number of threads must be greater than 0");
        }
        this.numThreads = numThreads;
    }


    /**
     * Searches for non-prime numbers in the given two-dimensional
     * integer array using multiple threads.
     *
     * @param array The array to be searched for non-prime numbers.
     * @return true if a non-prime number is found, otherwise false.
     */
    @Override
    public boolean search(int @NotNull [] array) {
        int sliceSize = array.length / numThreads;

        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        try {
            List<Future<Boolean>> results = new ArrayList<>();

            for (int i = 0; i < numThreads; i++) {
                int start = i * sliceSize;
                int end = (i == numThreads - 1) ? array.length : (i + 1) * sliceSize;

                Callable<Boolean> task = () -> searchSlice(array, start, end);

                results.add(pool.submit(task));
            }

            for (Future<Boolean> result : results) {
                if (result.get()) {
                    return true;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // Print the exception details
        } finally {
            pool.shutdownNow();
        }

        return false;
    }

    /**
     * Searches a slice of the array for non-prime numbers within the specified range.
     *
     * @param array The array to be searched.
     * @param start The starting index of the slice.
     * @param end The ending index of the slice.
     * @return true if a non-prime number is found in the slice, otherwise false.
     */
    private boolean searchSlice(int[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            if (Thread.currentThread().isInterrupted()) {
                // Handle interruption by stopping the processing
                return false;
            }

            if (!isPrime(array[i])) {
                return true;
            }
        }
        return false;
    }
}