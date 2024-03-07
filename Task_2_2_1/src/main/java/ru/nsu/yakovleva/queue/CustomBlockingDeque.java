package ru.nsu.yakovleva.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CustomBlockingDeque<T> {
    private final int size;
    private final Deque<T> deque = new ArrayDeque<>();

    public CustomBlockingDeque(int size) {
        this.size = Math.max(size, 0);
    }

    public synchronized boolean isEmpty() {
        return deque.isEmpty();
    }

    public synchronized int getSize() {
        return deque.size();
    }

    public synchronized T get() throws InterruptedException {
        while (deque.isEmpty()) wait();
        T object = deque.pop();
        notifyAll();
        return object;
    }

    public synchronized List<T> get(int amount) throws IllegalArgumentException, InterruptedException {
        if (amount < 1 || amount > size) throw new IllegalArgumentException();
        while (deque.isEmpty()) wait();
        List<T> objects = new ArrayList<>();
        while (!deque.isEmpty() && objects.size() != amount) objects.add(deque.pop());
        return objects;
    }

    public synchronized void put(T object) throws NullPointerException, InterruptedException {
        if (object == null) throw new NullPointerException();
        while (deque.size() == size) wait();
        deque.push(object);
        notifyAll();
    }
}