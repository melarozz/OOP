package ru.nsu.yakovleva.notprime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class TestClass {
    /**
     * App task class.
     */
    private static class AppTask implements Runnable {
        private final App App;
        private boolean result = false;

        /**
         * Class constructor.
         *
         * @param App - object App
         */
        private AppTask(App App) {
            this.App = App;
        }

        /**
         * Run method.
         */
        @Override
        public void run() {
            try {
                this.result = this.App.checkForNonPrimes();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Worker task class.
     */
    private static class WorkerTask implements Runnable {
        private final Worker worker;
        private final boolean interrupt;

        /**
         * Class constructor.
         *
         * @param worker - object worker
         */
        private WorkerTask(Worker worker, boolean interrupt) {
            this.worker = worker;
            this.interrupt = interrupt;
        }

        /**
         * Run method.
         */
        @Override
        public void run() {
            try {
                this.worker.receiveSignal();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.worker.calculateTask(interrupt);
        }
    }

    /**
     * Support method for constructing tests.
     *
     * @param array        - array of numbers
     * @param workersCount - number of workers
     * @param chunks       - chunks
     * @param result       - expected result
     */
    public void testConstructor(int[] array, int workersCount, int chunks,
                                boolean result, boolean interrupt)
            throws InterruptedException {
        App App = new App("230.0.0.0", 8000, 8001, array, chunks);
        AppTask AppTask = new AppTask(App);
        Thread AppThread = new Thread(AppTask);
        WorkerTask[] workerTasks = new WorkerTask[workersCount];
        Thread[] workerThread = new Thread[workersCount];
        for (int i = 0; i < workersCount; i++) {
            Worker worker = new Worker("230.0.0.0", 8000);
            if (interrupt && i == 0) {
                workerTasks[i] = new WorkerTask(worker, true);
            } else {
                workerTasks[i] = new WorkerTask(worker, false);
            }
            workerThread[i] = new Thread(workerTasks[i]);
            workerThread[i].start();
        }
        Thread.sleep(1000);
        AppThread.start();
        AppThread.join();
        for (int i = 0; i < workersCount; i++) {
            workerThread[i].join();
        }

        if (result) {
            assertTrue(AppTask.result);
        } else {
            assertFalse(AppTask.result);
        }
    }

    @Test
    public void trueTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[10] = 4;
        testConstructor(array, 5, 10, true, false);
    }

    @Test
    public void falseTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 5, 10, false, false);
    }

    @Test
    public void isPrimeTest() throws ExecutionException, InterruptedException {
        int[] array = new int[1000];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[10] = 4;
        NotPrimeSearch calculator = new NotPrimeSearch(array);
        assertTrue(calculator.getResult());
    }
}