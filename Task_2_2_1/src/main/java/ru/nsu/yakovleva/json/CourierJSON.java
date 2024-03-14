package ru.nsu.yakovleva.json;

/**
 * Represents JSON data for a courier, including their ID and bag capacity.
 */
public class CourierJSON {

    // Courier's unique identifier
    private int id;

    // Courier's bag capacity
    private int bagCapacity;

    /**
     * Retrieves the courier's ID.
     *
     * @return The courier's ID.
     */
    public int id() {
        return id;
    }

    /**
     * Retrieves the courier's bag capacity.
     *
     * @return The courier's bag capacity.
     */
    public int bagCapacity() {
        return bagCapacity;
    }
}
