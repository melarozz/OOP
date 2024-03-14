package ru.nsu.yakovleva.customer;

import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;

/**
 * Represents a group of customers that continuously generate orders and place them in a shared queue.
 */
public class Customers implements Runnable {

    // Flag to control the execution of the run loop
    private boolean areCustomersRunning;

    // Reference to the shared queue for orders
    private final CustomBlockingDeque<Order> queue;

    /**
     * Constructs a new Customers object with the given queue.
     *
     * @param queue The shared queue where orders will be placed.
     */
    public Customers(CustomBlockingDeque<Order> queue) {
        // Initializing the flag to false
        areCustomersRunning = false;
        // Assigning the provided queue to the local variable
        this.queue = queue;
    }

    /**
     * The main run method of the Customers thread, responsible for continuously generating orders.
     */
    @Override
    public void run() {
        // Setting the flag to true, starting the loop
        areCustomersRunning = true;

        // Loop for producing orders by creating Customer objects and invoking their produce method
        for (int i = 0; areCustomersRunning; ++i) {
            new Customer(queue).produce(new Order(i));

            try {
                Thread.sleep(1000); // 1000 milliseconds = 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the execution of the run loop.
     */
    public void stop() {
        // Setting the flag to false
        areCustomersRunning = false;
    }
}
