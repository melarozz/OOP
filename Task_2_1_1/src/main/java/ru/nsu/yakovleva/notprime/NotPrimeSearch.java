package ru.nsu.yakovleva.notprime;

import java.util.concurrent.ExecutionException;

/**
 * The NotPrimeSearch class provides methods for checking if a num is prime
 * and searching for non-prime nums in an array.
 */
public class NotPrimeSearch {

    /**
     * Checks if a given num is prime.
     *
     * @param num The num to be checked.
     * @return true if the num is prime, false otherwise.
     */
    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Searches for non-prime nums in the given array.
     *
     * @param array The array of integers to be searched.
     * @return true if there is a non-prime num in the array, false otherwise.
     * @throws ExecutionException   If an execution exception occurs during the search.
     * @throws InterruptedException If the search is interrupted.
     * @throws NullPointerException If the input array is null.
     */
    public boolean search(int[] array) throws ExecutionException,
            InterruptedException, NullPointerException {
        if (array == null) {
            throw new NullPointerException();
        }

        for (int num : array) {
            if (!isPrime(num)) {
                return true;
            }
        }

        return false;
    }
}
