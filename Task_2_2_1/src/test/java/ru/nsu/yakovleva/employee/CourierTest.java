package ru.nsu.yakovleva.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nsu.yakovleva.order.State.DELIVERED;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;

class CourierTest {
    private static final int MAX_STORAGE_SIZE = 20;
    private static final int MAX_ORDERS_NUMBER = 20;
    private static final int COURIERS_NUMBER = 3;
    private static final int BAG_CAPACITY = 3;
    private static final int ID = 0;
    private CustomBlockingDeque<Order> storage;
    private List<Order> orders;
    private final Random random = new Random();

    @BeforeEach
    public void init() throws InterruptedException {
        storage = new CustomBlockingDeque<>(MAX_STORAGE_SIZE);
        orders = new ArrayList<>();
        int ordersNumber = random.nextInt(MAX_ORDERS_NUMBER);
        for (int i = 0; i < ordersNumber; ++i) {
            Order order = new Order(i);
            orders.add(order);
        }
        for (Order order : orders) {
            storage.put(order);
        }
    }

    @Test
    public void consume() {
        Courier courier = new Courier(ID, BAG_CAPACITY, storage);
        while (!storage.isEmpty()) {
            List<Order> consumed = courier.take();
            consumed.forEach(order -> {
                assertEquals(DELIVERED, order.getState());
                assertTrue(orders.contains(order));
            });
        }
    }

    @Test
    public void work() {
        Courier courier = new Courier(ID, BAG_CAPACITY, storage);
        while (!storage.isEmpty()) {
            courier.work();
        }
        orders.forEach(order -> assertEquals(DELIVERED, order.getState()));
    }

    @Test
    public void couriers() throws InterruptedException {
        List<Courier> couriers = new ArrayList<>();
        for (int i = 0; i < COURIERS_NUMBER; ++i) {
            couriers.add(new Courier(i, BAG_CAPACITY, storage));
        }
        couriers.forEach(courier -> new Thread(courier).start());
        while (!storage.isEmpty()) {
        }
        Thread.sleep(1000);
        couriers.forEach(Courier::stop);
        orders.forEach(order -> assertEquals(DELIVERED, order.getState()));
    }
}