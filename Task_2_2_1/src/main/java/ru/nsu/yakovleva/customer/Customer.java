package ru.nsu.yakovleva.customer;

import java.util.Random;
import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.order.State;
import ru.nsu.yakovleva.producer.Producer;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;

/**
 * Represents a customer who places orders in a system.
 */
public class Customer implements Producer<Order> {

    // Maximum ordering time constant
    private static final int MAX_ORDERING_TIME = 100;

    // Random number generator for simulating ordering time
    private final Random random;

    // Reference to the blocking deque where orders are placed
    private final CustomBlockingDeque<Order> queue;

    /**
     * Constructs a new Customer object with the given queue.
     *
     * @param queue The blocking deque where orders are placed.
     */
    public Customer(CustomBlockingDeque<Order> queue) {
        random = new Random();
        this.queue = queue;
    }

    /**
     * Simulates the process of a customer placing an order.
     *
     * @param order The order to be placed.
     */
    @Override
    public void produce(Order order) {
        try {
            // Simulate random ordering time with sleep
            Thread.sleep(random.nextInt(MAX_ORDERING_TIME));

            // Set the order state to IN_QUEUE
            order.setState(State.IN_QUEUE);

            // Put the order into the blocking deque
            queue.put(order);
        } catch (NullPointerException | InterruptedException exception) {
            // Handle potential exceptions during order placement
            System.err.println("Error placing order: " + exception.getMessage());
        }
    }
}


