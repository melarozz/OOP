package ru.nsu.yakovleva.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.order.Order;
import ru.nsu.yakovleva.queue.CustomBlockingDeque;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.yakovleva.order.State.IN_QUEUE;

class CustomersTest {
    private final static int MAX_QUEUE_SIZE = 100;
    private CustomBlockingDeque<Order> queue;
    private int queueSize;
    private final Random random = new Random();

    @BeforeEach
    public void init() {
        queueSize = random.nextInt(MAX_QUEUE_SIZE);
        queue = new CustomBlockingDeque<>(queueSize);
    }

    @Test
    public void customers() throws InterruptedException {
        Customers customers = new Customers(queue);
        Thread customersThread = new Thread(new Customers(queue));
        customersThread.start();
        while (queue.getSize() != queueSize) {
        }
        Thread.sleep(100);
        customers.stop();
        List<Order> orders = queue.get(queueSize);
        orders.forEach(order -> assertEquals(IN_QUEUE, order.getState()));
    }

    @Test
    public void customersWithZeroQueueSize() {
        // Test with a queue size of 0
        CustomBlockingDeque<Order> emptyQueue = new CustomBlockingDeque<>(0);
        Customers customers = new Customers(emptyQueue);

        // No exception should be thrown when creating customers with an empty queue
        assertDoesNotThrow(() -> new Thread(customers));
        assertEquals(0, emptyQueue.getSize());
    }

    @Test
    public void customersWithNegativeQueueSize() {
        // Test with a queue size of 0
        CustomBlockingDeque<Order> emptyQueue = new CustomBlockingDeque<>(-10);
        Customers customers = new Customers(emptyQueue);

        // No exception should be thrown when creating customers with a negative number
        assertDoesNotThrow(() -> new Thread(customers));
        assertEquals(0, emptyQueue.getSize());
    }

}