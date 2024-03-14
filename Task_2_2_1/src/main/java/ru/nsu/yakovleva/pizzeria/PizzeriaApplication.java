package ru.nsu.yakovleva.pizzeria;

import ru.nsu.yakovleva.json.BakerJson;
import ru.nsu.yakovleva.json.CourierJson;
import ru.nsu.yakovleva.json.PizzeriaJson;
import ru.nsu.yakovleva.json.ReadJson;

/**
 * Represents the main application for running the pizzeria simulation.
 */
public class PizzeriaApplication implements Runnable {

    // Running time for the Pizzeria application in milliseconds
    private static final long RUNNING_TIME = 30 * 1000;

    // Pizzeria configuration read from JSON
    PizzeriaJson pizzeriaJson;

    // Instance of the Pizzeria class
    Pizzeria pizzeria;

    /**
     * Method to read Pizzeria configuration from JSON
      */
    public void setPizzeriaJson() {
        ReadJson jsonReader = new ReadJson();
        jsonReader.open();
        pizzeriaJson = jsonReader.read();
        jsonReader.close();
    }

    /**
     * Method to initialize Pizzeria based on the read configuration
     */
    public void setPizzeria() {
        if (pizzeriaJson == null) {
            System.err.println("Missing pizzeria configuration.");
            return;
        }
        if (pizzeriaJson.queueSize() <= 0) {
            System.err.println("Queue size must be a positive number.");
            return;
        }
        if (pizzeriaJson.storageSize() <= 0) {
            System.err.println("Storage size must be a positive number.");
            return;
        }
        BakerJson[] bakersJson = pizzeriaJson.bakers();
        if (bakersJson == null || bakersJson.length == 0) {
            System.err.println("Add bakers to the pizzeria configuration.");
            return;
        }
        CourierJson[] couriersJson = pizzeriaJson.couriers();
        if (couriersJson == null || couriersJson.length == 0) {
            System.err.println("Add couriers to the pizzeria configuration.");
            return;
        }
        pizzeria = new Pizzeria(pizzeriaJson);
    }

    // Constructor to set up the PizzeriaApplication
    public PizzeriaApplication() {
        setPizzeriaJson();
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