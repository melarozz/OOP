package ru.nsu.yakovleva.employee;

public abstract class Employee implements Runnable {

    // Employee's unique identifier
    private final int id;

    // Flag to control the execution of the runEmployee loop
    private boolean isEmployeeRunning;

    // Constructor to initialize the Employee with a unique identifier
    public Employee(int id) {
        this.id = id;
        // Initializing the runEmployee flag to false
        this.isEmployeeRunning = false;
    }

    // Getter method to retrieve the Employee's ID
    public int getId() {
        return id;
    }

    // Abstract method representing the work that an Employee performs
    abstract void work();

    // Overriding the run method from the Runnable interface
    @Override
    public void run() {
        // Setting the runEmployee flag to true, starting the work loop
        isEmployeeRunning = true;
        // Continuous execution of the work method as long as runEmployee is true
        while (isEmployeeRunning) {
            work();
        }
    }

    // Method to stop the execution of the Employee's work
    public void stop() {
        isEmployeeRunning = false;
    }
}