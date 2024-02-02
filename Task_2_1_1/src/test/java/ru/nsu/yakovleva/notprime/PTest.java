package ru.nsu.yakovleva.notprime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;

/**
 * The PTest class contains performance tests for various prime number search algorithms.
 */
public class PTest {
    /**
     * Creates an array of the specified size with all
     * elements except the last set to a constant value,
     * and the last element set to a different value.
     *
     * @param size The size of the array.
     * @return The created array.
     * @throws IllegalArgumentException if the size is less than or equal to 0.
     */
    private int[] createArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 1073676287);
        array[size - 1] = 187263196;
        return array;
    }

    /**
     * Performs a single performance test for
     * the given NotPrimeSearch implementation on the specified array.
     *
     * @param notPrimeSearch The NotPrimeSearch implementation to test.
     * @param array The array for the prime number search.
     * @return The minimum execution time of three test runs.
     * @throws ExecutionException   if the computation threw an exception.
     * @throws InterruptedException if the current thread was interrupted while waiting.
     */
    private long singleTest(NotPrimeSearch notPrimeSearch, int[] array) throws
            ExecutionException, InterruptedException {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            long time = System.nanoTime();
            notPrimeSearch.search(array);
            time = System.nanoTime() - time;
            results.add(time);
        }
        return Collections.min(results);
    }

    /**
     * Conducts a performance test for different
     * prime number search algorithms and generates a comparison chart.
     *
     * @throws ExecutionException   if the computation threw an exception.
     * @throws InterruptedException if the current thread was interrupted while waiting.
     * @throws IOException          if an I/O error occurs during file creation or saving.
     */
    @Test
    public void performanceTest() throws ExecutionException, InterruptedException, IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Regular");
        XYSeries series2 = new XYSeries("Thread 2");
        XYSeries series3 = new XYSeries("Thread 4");
        XYSeries series4 = new XYSeries("Thread 8");
        XYSeries series5 = new XYSeries("Stream");

        for (int size = 10; size <= 10000; size *= 10) {
            int[] array = createArray(size);
            series1.add(size, singleTest(new NotPrimeSearch(), array));
            series2.add(size, singleTest(new ThreadNotPrimeSearch(2), array));
            series3.add(size, singleTest(new ThreadNotPrimeSearch(4), array));
            series4.add(size, singleTest(new ThreadNotPrimeSearch(8), array));
            series5.add(size, singleTest(new StreamNotPrimeSearch(), array));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(series5);
        PerformanceTest chart = new PerformanceTest(dataset);
        chart.createFile();
    }
}