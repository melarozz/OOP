package ru.nsu.yakovleva.pizzeria;

import ru.nsu.yakovleva.json.BakerJSON;
import ru.nsu.yakovleva.json.CourierJSON;
import ru.nsu.yakovleva.json.ReadJSON;
import ru.nsu.yakovleva.json.PizzeriaJSON;

/**
 * Represents the main application for running the pizzeria simulation.
 */
public class PizzeriaApplication implements Runnable {

    // Running time for the Pizzeria application in milliseconds
    private final static long RUNNING_TIME = 30 * 1000;

    // Pizzeria configuration read from JSON
    private PizzeriaJSON pizzeriaJSON;

    // Instance of the Pizzeria class
    private Pizzeria pizzeria;

    // Method to read Pizzeria configuration from JSON
    private void setPizzeriaJSON() {
        ReadJSON jsonReader = new ReadJSON();
        jsonReader.open();
        pizzeriaJSON = jsonReader.read();
        jsonReader.close();
    }

    // Method to initialize Pizzeria based on the read configuration
    private void setPizzeria() {
        if (pizzeriaJSON == null) {
            System.err.println("Missing pizzeria configuration.");
            return;
        }
        if (pizzeriaJSON.queueSize() <= 0) {
            System.err.println("Queue size must be a positive number.");
            return;
        }
        if (pizzeriaJSON.storageSize() <= 0) {
            System.err.println("Storage size must be a positive number.");
            return;
        }
        BakerJSON[] bakersJSON = pizzeriaJSON.bakers();
        if (bakersJSON == null || bakersJSON.length == 0) {
            System.err.println("Add bakers to the pizzeria configuration.");
            return;
        }
        CourierJSON[] couriersJSON = pizzeriaJSON.couriers();
        if (couriersJSON == null || couriersJSON.length == 0) {
            System.err.println("Add couriers to the pizzeria configuration.");
            return;
        }
        pizzeria = new Pizzeria(pizzeriaJSON);
    }

    // Constructor to set up the PizzeriaApplication
    public PizzeriaApplication() {
        setPizzeriaJSON();
        setPizzeria();
    }

    // Overriding the run method from the Runnable interface
    @Override
    public void run() {
        // Checking if the Pizzeria instance is successfully initialized
        if (pizzeria == null) {
            System.err.println("Failed to start application.");
            System.exit(1);
        }

        // Starting the Pizzeria thread
        Thread pizzeriaThread = new Thread(pizzeria);
        pizzeriaThread.start();

        try {
            // Running the Pizzeria application for a specified time
            Thread.sleep(RUNNING_TIME);

            // Stopping the Pizzeria and exiting the application
            pizzeria.stop();
            System.exit(0);

        } catch (InterruptedException exception) {
            System.err.println("The application finished with an error.");
            System.exit(2);
        }
    }
}