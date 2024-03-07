package ru.nsu.yakovleva.customer;

import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.producer.Producer;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;
import ru.nsu.yakovleva.order.State;

import java.util.Random;

public class Customer implements Producer<Order> {

    // Maximum ordering time constant
    private static final int MAX_ORDERING_TIME = 100;

    // Random number generator for simulating ordering time
    private final Random random;

    // Reference to the blocking deque where orders are placed
    private final CustomBlockingDeque<Order> queue;

    // Constructor initializing the random number generator and the queue reference
    public Customer(CustomBlockingDeque<Order> queue) {
        random = new Random();
        this.queue = queue;
    }

    // Implementation of the produce method from the Producer interface
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