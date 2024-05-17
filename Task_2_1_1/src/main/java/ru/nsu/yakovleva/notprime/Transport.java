package ru.nsu.yakovleva.notprime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Data transporter class.
 */
public class Transport {
    private final String host;
    private final int broadcastPortNumber;
    private final int tcpPortNumber;
    private final BlockingQueue<String> subArrays;
    private ServerSocket mainSocket;
    private final BlockingQueue<Socket> workers;
    private final BlockingQueue<Boolean> results;
    private final BlockingQueue<String> chunksQueue;
    private final ArrayList<Socket> closeWorkers;

    /**
     * Class constructor.
     *
     * @param host          - ip address
     * @param broadcastPortNumber - broadcast port
     * @param tcpPortNumber       - tcp port
     * @param chunks        - chunks
     * @param subArrays     - queue with wtrings
     */
    public Transport(String host, int broadcastPortNumber, int tcpPortNumber,
                           int chunks, BlockingQueue<String> subArrays) {
        this.host = host;
        this.broadcastPortNumber = broadcastPortNumber;
        this.tcpPortNumber = tcpPortNumber;
        this.subArrays = subArrays;
        this.workers = new LinkedBlockingDeque<>();
        this.results = new LinkedBlockingDeque<>(chunks);
        this.chunksQueue = new LinkedBlockingDeque<>(chunks);
        this.closeWorkers = new ArrayList<>();
    }

    /**
     * Send signal to the workers.
     */
    public void sendSignal() throws IOException {
        try (DatagramSocket signalSenderSocket = new DatagramSocket()) {
            InetAddress group = InetAddress.getByName(this.host);
            String message = Integer.toString(this.tcpPortNumber);
            byte[] buffer = message.getBytes();
            DatagramPacket pack = new DatagramPacket(buffer, buffer.length, group, broadcastPortNumber);
            signalSenderSocket.send(pack);
            this.mainSocket = new ServerSocket(this.tcpPortNumber);
        }

    }

    /**
     * Send tasks to the workers.
     *
     * @return thread
     */
    public Thread sendTask() {
        Thread sendTaskThread = new Thread(() -> {
            try {
                while (this.results.remainingCapacity() != 0) {
                    if (!this.subArrays.isEmpty()) {
                        Socket workerSocket = this.mainSocket.accept();
                        this.workers.put(workerSocket);
                        this.closeWorkers.add(workerSocket);
                        PrintWriter out = new PrintWriter(workerSocket.getOutputStream(), true);
                        String message = this.subArrays.take();
                        out.println(message);
                        this.chunksQueue.put(message);
                    }
                }
            } catch (IOException | InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });
        sendTaskThread.start();
        return sendTaskThread;
    }


    /**
     * Get result from workers.
     *
     * @return thread
     */
    public Thread receiveResult() {
        Thread receiveResultThread = new Thread(() -> {
            try {
                while (this.results.remainingCapacity() != 0) {
                    String result = null;
                    Socket workerSocket = this.workers.take();
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()))) {
                        result = in.readLine();
                    } catch (IOException e) {
                        String message = this.chunksQueue.take();
                        this.subArrays.put(message);
                        continue;
                    }
                    try {
                        this.results.put(Boolean.parseBoolean(result));
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });
        receiveResultThread.start();
        return receiveResultThread;
    }


    /**
     * Get result method.
     *
     * @return true or false
     */
    public boolean getResult() throws InterruptedException {
        while (!this.results.isEmpty()) {
            if (this.results.take()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Close all sockets.
     */
    public void closeWorkers() throws IOException {
        this.mainSocket.close();
        for (Socket closeWorker : this.closeWorkers) {
            closeWorker.close();
        }
    }
}