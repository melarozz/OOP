package ru.nsu.yakovleva.customer;

import ru.nsu.yakovleva.queue.CustomBlockingDeque;
import ru.nsu.yakovleva.order.Order;

public class Customers implements Runnable {

    // Flag to control the execution of the runCustomers loop
    private boolean areCustomersRunning;

    // Reference to the shared queue for orders
    private final CustomBlockingDeque<Order> queue;

    // Constructor that initializes the Customers object with a given queue
    public Customers(CustomBlockingDeque<Order> queue) {
        // Initializing the runCustomers flag to false
        areCustomersRunning = false;
        // Assigning the provided queue to the local variable
        this.queue = queue;
    }

    @Override
    public void run() {
        // Setting the runCustomers flag to true, starting the loop
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

    // Method to stop the execution of the runCustomers loop
    public void stop() {
        // Setting the runCustomers flag to false
        areCustomersRunning = false;
    }
}