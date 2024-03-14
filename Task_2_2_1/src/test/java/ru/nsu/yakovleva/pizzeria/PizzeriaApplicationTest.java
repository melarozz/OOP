package ru.nsu.yakovleva.pizzeria;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class PizzeriaApplicationTest {

    @Test
    public void testPizzeriaConfigurationSetup() {
        PizzeriaApplication app = new PizzeriaApplication();
        assertNotNull(app.pizzeriaJson, "Pizzeria configuration should not be null");
    }

    @Test
    public void testPizzeriaInitialization() {
        PizzeriaApplication app = new PizzeriaApplication();
        assertNotNull(app.pizzeria, "Pizzeria instance should not be null");
    }



    @Test
    public void testConcurrentAccess() {
        // Simulate concurrent access to shared resources
        PizzeriaApplication app = new PizzeriaApplication();

        Thread thread1 = new Thread(() -> {
            try {
                assertNotNull(app.pizzeria.queue, "Queue should not be null");
            } catch (Throwable t) {
                fail("Unexpected exception occurred: " + t.getMessage());
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                assertNotNull(app.pizzeria.storage, "Storage should not be null");
            } catch (Throwable t) {
                fail("Unexpected exception occurred: " + t.getMessage());
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}