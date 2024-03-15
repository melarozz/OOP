package ru.nsu.yakovleva.employee;

/**
 * Represents an abstract employee capable of performing work.
 */
public abstract class Employee implements Runnable {

    // Employee's unique identifier
    private final int id;

    // Flag to control the execution of the work loop
    private boolean isEmployeeRunning;

    /**
     * Constructs a new Employee object with the given unique identifier.
     *
     * @param id The unique identifier of the employee.
     */
    public Employee(int id) {
        this.id = id;
        // Initializing the flag to false
        this.isEmployeeRunning = false;
    }

    /**
     * Retrieves the unique identifier of the employee.
     *
     * @return The ID of the employee.
     */
    public int getId() {
        return id;
    }

    /**
     * Represents the work that an employee performs. This method must be implemented by subclasses.
     */
    abstract void work();

    /**
     * Overrides the run method from the Runnable interface, starting the work loop.
     */
    @Override
    public void run() {
        // Setting the flag to true, starting the work loop
        isEmployeeRunning = true;
        // Continuous execution of the work method as long as the flag is true
        while (isEmployeeRunning) {
            work();
        }
    }

    /**
     * Stops the execution of the employee's work loop.
     */
    public void stop() {
        isEmployeeRunning = false;
    }
}
