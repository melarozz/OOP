package ru.nsu.yakovleva.json;

/**
 * Represents JSON data for a pizzeria, including queue size, storage size, bakers, and couriers.
 */
public class PizzeriaJson {

    // Size of the queue for incoming orders
    private int queueSize;

    // Size of the storage for finished orders
    private int storageSize;

    // Array of BakerJSON objects representing the bakers in the pizzeria
    private BakerJson[] bakers;

    // Array of CourierJSON objects representing the couriers in the pizzeria
    private CourierJson[] couriers;

    /**
     * Retrieves the size of the queue for incoming orders.
     *
     * @return The size of the queue.
     */
    public int queueSize() {
        return queueSize;
    }

    /**
     * Retrieves the size of the storage for finished orders.
     *
     * @return The size of the storage.
     */
    public int storageSize() {
        return storageSize;
    }

    /**
     * Retrieves the array of BakerJSON objects representing the bakers in the pizzeria.
     *
     * @return The array of bakers.
     */
    public BakerJson[] bakers() {
        return bakers;
    }

    /**
     * Retrieves the array of CourierJSON objects representing the couriers in the pizzeria.
     *
     * @return The array of couriers.
     */
    public CourierJson[] couriers() {
        return couriers;
    }
}
