package ru.nsu.yakovleva.producer;

public interface Producer<T> {
    void produce(T object);
}
