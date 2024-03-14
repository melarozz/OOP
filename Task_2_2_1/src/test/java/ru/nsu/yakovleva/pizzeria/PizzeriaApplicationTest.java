package ru.nsu.yakovleva.pizzeria;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}