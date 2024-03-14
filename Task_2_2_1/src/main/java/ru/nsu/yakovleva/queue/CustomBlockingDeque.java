package ru.nsu.yakovleva.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Custom blocking deque implementation with a fixed size.
 *
 * @param <T> The type of elements stored in the deque.
 */
public class CustomBlockingDeque<T> {

    // Maximum size of the deque
    private final int size;

    // Deque to store elements
    private final Deque<T> deque = new ArrayDeque<>();

    /**
     * Constructs a new CustomBlockingDeque with the specified size.
     *
     * @param size The maximum size of the deque.
     */
    public CustomBlockingDeque(int size) {
        this.size = Math.max(size, 0);
    }

    /**
     * Checks if the deque is empty.
     *
     * @return true if the deque is empty, false otherwise.
     */
    public synchronized boolean isEmpty() {
        return deque.isEmpty();
    }

    /**
     * Gets the current size of the deque.
     *
     * @return The number of elements in the deque.
     */
    public synchronized int getSize() {
        return deque.size();
    }

    /**
     * Retrieves and removes the first element of the deque.
     *
     * @return The first element of the deque.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public synchronized T get() throws InterruptedException {
        while (deque.isEmpty()) wait();
        T object = deque.pop();
        notifyAll();
        return object;
    }

    /**
     * Retrieves and removes the specified number of elements from the deque.
     *
     * @param amount The number of elements to retrieve.
     * @return A list containing the retrieved elements.
     * @throws IllegalArgumentException If the specified amount is invalid.
     * @throws InterruptedException     If the thread is interrupted while waiting.
     */
    public synchronized List<T> get(int amount) throws IllegalArgumentException, InterruptedException {
        if (amount < 1 || amount > size) throw new IllegalArgumentException();
        while (deque.isEmpty()) wait();
        List<T> objects = new ArrayList<>();
        while (!deque.isEmpty() && objects.size() != amount) objects.add(deque.pop());
        return objects;
    }

    /**
     * Inserts the specified element into the deque.
     *
     * @param object The element to be inserted.
     * @throws NullPointerException  If the specified element is null.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public synchronized void put(T object) throws NullPointerException, InterruptedException {
        if (object == null) throw new NullPointerException();
        while (deque.size() == size) wait();
        deque.push(object);
        notifyAll();
    }
}