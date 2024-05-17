package ru.nsu.yakovleva.notprime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Worker class.
 */
public class Worker {
    private final String host;
    private final int port1;
    private int port2;

    /**
     * Class constructor.
     *
     * @param host  - ip address
     * @param port1 - broadcast port
     */
    public Worker(String host, int port1) {
        this.host = host;
        this.port1 = port1;
    }

    /**
     * Method to get broadcast signal.
     */
    public void receiveSignal() throws IOException {
        try (MulticastSocket signalReceiverSocket = new MulticastSocket(this.port1)) {
            InetAddress group = InetAddress.getByName(this.host);
            signalReceiverSocket.joinGroup(group);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            signalReceiverSocket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            this.port2 = Integer.parseInt(message);
            signalReceiverSocket.leaveGroup(group);
        }
    }


    /**
     * Main method.
     */
    public void calculateTask(boolean interrupt) {
        while (true) {
            try (Socket workerSocket = new Socket("localhost", this.port2);
                 BufferedReader in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(workerSocket.getOutputStream(), true)) {

                if (interrupt) {
                    break;
                }

                String mainMessage = in.readLine();
                int[] array = Preprocess.makeArray(mainMessage);
                NotPrimeSearch calculator = new NotPrimeSearch(array);
                boolean result = calculator.getResult();
                out.println(result ? "true" : "false");

            } catch (IOException e) {
                break;
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}