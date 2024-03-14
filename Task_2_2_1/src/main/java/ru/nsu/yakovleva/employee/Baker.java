package ru.nsu.yakovleva.employee;

import static ru.nsu.yakovleva.order.State.COOKING;
import static ru.nsu.yakovleva.order.State.IN_STOCK;

import java.util.Random;
import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.producer.Producer;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;

/**
 * Represents a baker who takes orders from a queue, cooks them, and places them in storage.
 */
public class Baker extends Employee implements Producer<Order> {

    // Constant representing the maximum cooking time for an order
    private static final int MAX_COOKING_TIME = 5000;

    // Variable to store the working experience of the baker
    private final int workingExperience;

    // Random object for generating random cooking times
    private final Random random;

    // Reference to the shared queue for incoming orders
    private final CustomBlockingDeque<Order> queue;

    // Reference to the shared storage for finished orders
    private final CustomBlockingDeque<Order> storage;

    /**
     * Constructs a new Baker object with the specified parameters.
     *
     * @param id                The unique identifier of the baker.
     * @param workingExperience The working experience of the baker.
     * @param queue             The shared queue where incoming orders are placed.
     * @param storage           The shared storage where finished orders are placed.
     */
    public Baker(int id, int workingExperience, CustomBlockingDeque<Order> queue,
                 CustomBlockingDeque<Order> storage) {
        // Calling the constructor of the superclass (Employee)
        super(id);
        // Initializing working experience, random, queue, and storage with provided values
        this.workingExperience = workingExperience;
        this.random = new Random();
        this.queue = queue;
        this.storage = storage;
    }

    /**
     * Takes an order from the queue and sets its state to cooking.
     *
     * @return The order taken from the queue.
     */
    public Order take() {
        try {
            // Attempting to retrieve an order from the queue and setting its state to COOKING
            Order order = queue.get();
            if (!Thread.interrupted()) {
                order.setState(COOKING);
                return order;
            } else {
                // If interrupted, stop the thread
                System.err.println("Baker #" + getId() + " cooking was interrupted.");
                return null;
            }
        } catch (InterruptedException exception) {
            // Handling an exception if the baker cannot take an order
            System.err.println("Baker #" + getId() + " couldn't take the order.");
            return null;
        }
    }

    /**
     * Cooks the provided order and places it in the storage.
     *
     * @param order The order to be cooked and placed in the storage.
     */
    @Override
    public void produce(Order order) {
        // Generating a random lead time for cooking based on working experience
        int leadTime = random.nextInt(MAX_COOKING_TIME) / workingExperience;
        try {
            // Simulating the cooking time by sleeping the thread
            Thread.sleep(leadTime);
            // Setting the order state to IN_STOCK and putting it in the storage
            order.setState(IN_STOCK);
            storage.put(order);
        } catch (NullPointerException | InterruptedException exception) {
            // Handling exceptions if there are issues performing the order
            System.err.println("Baker #" + getId() + " had an issue performing the order.");
        }
    }

    /**
     * Implements the overall work cycle of the baker: taking an order and producing it.
     */
    @Override
    public void work() {
        // Consuming an order from the queue
        Order order = take();
        // Stopping the baker if there is no order
        if (order == null) {
            stop();
        }
        // Producing the order (cooking and placing it in the storage)
        produce(order);
    }
}
