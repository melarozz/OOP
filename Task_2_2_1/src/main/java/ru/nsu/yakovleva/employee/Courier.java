package ru.nsu.yakovleva.employee;

import static ru.nsu.yakovleva.order.State.DELIVERING;
import static ru.nsu.yakovleva.order.State.DELIVERED;

import java.util.List;
import java.util.Random;
import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.order.State;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;

/**
 * Represents a courier who delivers orders from a storage.
 */
public class Courier extends Employee {

    // Constant representing the maximum delivery time for a set of orders
    private static final int MAX_DELIVERY_TIME = 1000;

    // Variable to store the bag capacity of the courier
    private final int bagCapacity;

    // List to store the orders being delivered
    private List<Order> orders;

    // Reference to the shared storage for delivered orders
    private final CustomBlockingDeque<Order> storage;

    // Random object for generating random delivery times
    private final Random random;

    /**
     * Constructs a new Courier object with the specified parameters.
     *
     * @param id           The unique identifier of the courier.
     * @param bagCapacity  The bag capacity of the courier.
     * @param storage      The shared storage where delivered orders are placed.
     */
    public Courier(int id, int bagCapacity, CustomBlockingDeque<Order> storage) {
        // Calling the constructor of the superclass (Employee)
        super(id);
        // Initializing bag capacity, storage, and random with provided values
        this.bagCapacity = bagCapacity;
        this.storage = storage;
        this.random = new Random();
    }

    /**
     * Sets the state of all orders in the list to a specified state.
     *
     * @param state The state to set for the orders.
     */
    private void setOrdersState(State state) {
        orders.forEach(order -> order.setState(state));
    }

    /**
     * Takes a set of orders from the storage, delivers them,
     * and updates their states accordingly.
     *
     * @return The set of orders taken and delivered.
     */
    public List<Order> take() {
        // Generating a random delivery time for the set of orders
        int deliveryTime = random.nextInt(MAX_DELIVERY_TIME);
        try {
            // Attempting to retrieve a set of orders from the storage
            orders = storage.get(bagCapacity);
            // Setting the state of orders to DELIVERING
            setOrdersState(DELIVERING);
            // Simulating the delivery time by sleeping the thread
            Thread.sleep(deliveryTime);
            // Setting the state of orders to DELIVERED
            setOrdersState(DELIVERED);
            return orders;
        } catch (InterruptedException exception) {
            // Handling an exception if the courier cannot deliver the order
            System.err.println("Courier #" + getId() + " couldn't deliver the order.");
            return null;
        }
    }

    /**
     * Implements the overall work cycle of the courier: taking orders from storage
     * and delivering them.
     */
    @Override
    void work() {
        // Consuming a set of orders from the storage
        List<Order> orders = take();
        // Stopping the courier if there are no orders
        if (orders == null) {
            stop();
        }
    }
}
