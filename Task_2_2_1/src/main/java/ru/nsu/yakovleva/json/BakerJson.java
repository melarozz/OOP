package ru.nsu.yakovleva.json;

/**
 * Represents JSON data for a baker, including their ID and working experience.
 */
public class BakerJson {

    // Baker's unique identifier
    private int id;

    // Baker's working experience
    private int workingExperience;

    /**
     * Retrieves the baker's ID.
     *
     * @return The baker's ID.
     */
    public int id() {
        return id;
    }

    /**
     * Retrieves the baker's working experience.
     *
     * @return The baker's working experience.
     */
    public int workingExperience() {
        return workingExperience;
    }
}
