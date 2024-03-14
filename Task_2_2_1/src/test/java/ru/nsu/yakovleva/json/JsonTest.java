package ru.nsu.yakovleva.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JsonTest {

    @Test
    public void testBakerJsonConstructorAndGetters() {
        PizzeriaJson pizzeriaJson;
        ReadJson jsonReader = new ReadJson();
        jsonReader.open();
        pizzeriaJson = jsonReader.read();
        jsonReader.close();
        BakerJson[] bakersJson = pizzeriaJson.bakers();
        assertEquals(4, bakersJson.length);
        for (int i = 0; i < 4; i++) {
            assertEquals(i, bakersJson[i].id());
        }
        assertEquals(2, bakersJson[0]. workingExperience());
        assertEquals(2, bakersJson[1]. workingExperience());
        assertEquals(3, bakersJson[2]. workingExperience());
        assertEquals(10, bakersJson[3]. workingExperience());
    }

    @Test
    public void testCourierJsonConstructorAndGetters() {
        PizzeriaJson pizzeriaJson;
        ReadJson jsonReader = new ReadJson("pizzeria.json");
        jsonReader.open();
        pizzeriaJson = jsonReader.read();
        jsonReader.close();
        CourierJson[] courierJson = pizzeriaJson.couriers();
        assertEquals(4, courierJson.length);
        for (int i = 0; i < 4; i++) {
            assertEquals(i, courierJson[i].id());
        }
        assertEquals(3, courierJson[0]. bagCapacity());
        assertEquals(2, courierJson[1]. bagCapacity());
        assertEquals(2, courierJson[2]. bagCapacity());
        assertEquals(5, courierJson[3]. bagCapacity());
    }

}