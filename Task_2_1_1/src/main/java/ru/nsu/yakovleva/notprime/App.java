package ru.nsu.yakovleva.notprime;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Application class for checking non-prime numbers.
 */
public class App {
    private final String serverAddress;
    private final int broadcastPortNumber;
    private final int tcpPortNumber;
    private final int[] numbers;
    private final int chunkSize;

    /**
     * Constructor.
     *
     * @param serverAddress       - server IP address
     * @param broadcastPortNumber - broadcast port number
     * @param tcpPortNumber       - TCP port number
     * @param numbers        - array of numbers to check
     * @param chunkSize           - size of chunks for processing
     */
    public App(String serverAddress, int broadcastPortNumber, int tcpPortNumber, int[] numbers, int chunkSize) {
        this.serverAddress = serverAddress;
        this.broadcastPortNumber = broadcastPortNumber;
        this.tcpPortNumber = tcpPortNumber;
        this.numbers = numbers;
        this.chunkSize = chunkSize;
    }

    /**
     * Main method for checking non-prime numbers.
     *
     * @return true if non-prime numbers are found, otherwise false
     * @throws IOException          - if an I/O error occurs
     * @throws InterruptedException - if the current thread is interrupted
     */
    public boolean checkForNonPrimes() throws IOException, InterruptedException {
        BlockingQueue<String> subArrays = Preprocess.arraySeparator(this.numbers, this.chunkSize);
        Transport transport = new Transport(this.serverAddress, this.broadcastPortNumber,
                this.tcpPortNumber, this.chunkSize, subArrays);
        transport.sendSignal();
        Thread sendTaskThread = transport.sendTask();
        Thread receiveResultThread = transport.receiveResult();
        sendTaskThread.join();
        receiveResultThread.join();
        transport.closeWorkers();
        return transport.getResult();
    }
}
