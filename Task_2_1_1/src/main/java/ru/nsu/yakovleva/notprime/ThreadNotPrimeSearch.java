package ru.nsu.yakovleva.notprime;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ThreadNotPrimeSearch extends NotPrimeSearch and uses multiple threads
 * to perform a parallel search for non-prime numbers in an integer array.
 */
public class ThreadNotPrimeSearch extends NotPrimeSearch {
    private Deque<Integer> nums;
    private final int numThreads;

    /**
     * Constructs a ThreadNotPrimeSearch object with the specified number of threads.
     *
     * @param numThreads The number of threads to use for the parallel search.
     * @throws IllegalArgumentException if the number of threads is less than or equal to 0.
     */
    public ThreadNotPrimeSearch(int numThreads) {
        if (numThreads <= 0) {
            throw new IllegalArgumentException("Number of threads must be greater than 0");
        }
        this.numThreads = numThreads;
    }

    /**
     * Retrieves the next integer from the queue in a synchronized manner.
     *
     * @return The next integer from the queue, or null if the queue is empty.
     */
    private synchronized Integer getNextNum() {
        return nums.poll();
    }

    /**
     * Searches for non-prime numbers in the given array using multiple threads.
     *
     * @param array The integer array to search.
     * @return true if a non-prime number is found, false otherwise.
     * @throws ExecutionException   if the computation threw an exception.
     * @throws InterruptedException if the current thread was interrupted while waiting.
     * @throws NullPointerException if the input array is null.
     */
    @Override
    public boolean search(int[] array) throws ExecutionException,
            InterruptedException, NullPointerException {
        if (array == null) {
            throw new NullPointerException();
        }

        nums = new ArrayDeque<>(Arrays.asList(Arrays
                .stream(array)
                .boxed()
                .toArray(Integer[]::new)));

        Callable<Boolean> task = () -> {
            Integer num;
            while ((num = getNextNum()) != null) {
                if (!isPrime(num)) {
                    return true;
                }
            }
            return false;
        };

        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        List<Future<Boolean>> results = pool.invokeAll(Collections.nCopies(numThreads, task));

        for (Future<Boolean> result : results) {
            if (result.get()) {
                pool.shutdownNow();
                return true;
            }
        }

        pool.shutdownNow();
        return false;
    }
}
