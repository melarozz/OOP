package ru.nsu.yakovleva.employee;

import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;
import ru.nsu.yakovleva.order.State;

import java.util.List;
import java.util.Random;

import static ru.nsu.yakovleva.order.State.*;

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

    // Constructor for creating a Courier object with specified parameters
    public Courier(int id, int bagCapacity, CustomBlockingDeque<Order> storage) {
        // Calling the constructor of the superclass (Employee)
        super(id);
        // Initializing bag capacity, storage, and random with provided values
        this.bagCapacity = bagCapacity;
        this.storage = storage;
        this.random = new Random();
    }

    // Method to set the state of all orders in the list to a specified state
    private void setOrdersState(State state) {
        orders.forEach(order -> order.setState(state));
    }

    // Implementation of the consume method from the Consumer interface
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

    // Implementation of the work method for the courier's overall work cycle
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