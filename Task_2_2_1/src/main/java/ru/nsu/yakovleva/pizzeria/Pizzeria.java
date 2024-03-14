package ru.nsu.yakovleva.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ru.nsu.yakovleva.customer.Customers;
import ru.nsu.yakovleva.employee.Baker;
import ru.nsu.yakovleva.employee.Courier;
import ru.nsu.yakovleva.json.BakerJson;
import ru.nsu.yakovleva.json.CourierJson;
import ru.nsu.yakovleva.json.PizzeriaJson;
import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;

/**
 * Represents a pizzeria with bakers, couriers, and customers.
 */
public class Pizzeria implements Runnable {

    // Flag to control the execution of the Pizzeria's run loop
    private boolean isPizzeriaRunning;

    // Lists to store instances of Bakers and Couriers
    private List<Baker> bakers;
    private List<Courier> couriers;

    // Customers and Queues for orders
    private final Customers customers;
    private final CustomBlockingDeque<Order> queue;
    private final CustomBlockingDeque<Order> storage;

    /**
     * Constructs a new Pizzeria based on the provided settings.
     *
     * @param settings The settings for the pizzeria.
     */
    public Pizzeria(PizzeriaJson settings) {
        this.isPizzeriaRunning = false;
        this.queue = new CustomBlockingDeque<>(settings.queueSize());
        this.storage = new CustomBlockingDeque<>(settings.storageSize());
        this.customers = new Customers(this.queue);
        setBakers(settings.bakers());
        setCouriers(settings.couriers());
    }

    // Method to initialize Bakers based on the provided BakerJSON array
    private void setBakers(BakerJson[] bakers) {
        Stream<BakerJson> bakerJSONStream = Arrays.stream(bakers);
        this.bakers = bakerJSONStream
                .map(bakerJson -> new Baker(bakerJson.id(),
                        bakerJson.workingExperience(), this.queue, this.storage))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Method to initialize Couriers based on the provided CourierJSON array
    private void setCouriers(CourierJson[] couriers) {
        Stream<CourierJson> courierJSONStream = Arrays.stream(couriers);
        this.couriers = courierJSONStream
                .map(courierJson -> new Courier(courierJson.id(),
                        courierJson.bagCapacity(), this.storage))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Overrides the run method from the Runnable interface, starting the Pizzeria's operation.
     */
    @Override
    public void run() {
        // Setting the runPizzeria flag to true, starting the Pizzeria loop
        isPizzeriaRunning = true;

        // Creating thread pools for Bakers and Couriers
        ExecutorService bakersThreadPool = Executors.newFixedThreadPool(bakers.size());
        ExecutorService couriersThreadPool = Executors.newFixedThreadPool(couriers.size());

        // Executing Bakers and Couriers in their respective thread pools
        bakers.forEach(bakersThreadPool::execute);
        couriers.forEach(couriersThreadPool::execute);

        // Starting the Customers thread
        Thread customersThread = new Thread(customers);
        customersThread.start();

        // Displaying a message indicating the Pizzeria is up and running
        System.out.println("The pizzeria is opened!");

        // Waiting for the Pizzeria loop to complete
        while (isPizzeriaRunning && !bakersThreadPool.isTerminated()
                && !couriersThreadPool.isTerminated()) {
        }

        // Handling termination scenarios
        if (bakersThreadPool.isTerminated() || couriersThreadPool.isTerminated()) {
            System.out.println("The pizzeria is closed because of technical issues.");
        }

        // Setting the runPizzeria flag to false and shutting down thread pools
        isPizzeriaRunning = false;
        bakersThreadPool.shutdownNow();
        couriersThreadPool.shutdownNow();
        customers.stop();
    }

    /**
     * Stops the execution of the Pizzeria.
     */
    public void stop() {
        System.out.println("The pizzeria is closed, goodbye!");
        isPizzeriaRunning = false;
    }
}