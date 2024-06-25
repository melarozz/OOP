package ru.nsu.yakovleva.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
class CustomBlockingDequeTest {

    @Test
    void testPutAndGet() throws InterruptedException {
        CustomBlockingDeque<Integer> deque = new CustomBlockingDeque<>(10);
        deque.put(5);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.getSize());
        assertEquals(5, deque.get());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.getSize());
    }

    @Test
    void testPutAndGetMultiple() throws InterruptedException {
        CustomBlockingDeque<Integer> deque = new CustomBlockingDeque<>(10);
        for (int i = 0; i < 5; i++) {
            deque.put(i);
        }
        assertEquals(5, deque.getSize());
        List<Integer> retrieved = deque.get(3);
        assertEquals(3, retrieved.size());
    }


}
