package ru.nsu.yakovleva.producer;

/**
 * Represents a producer that produces objects of a specified type.
 *
 * @param <T> The type of objects produced by the producer.
 */
public interface Producer<T> {

    /**
     * Produces an object of type T.
     *
     * @param object The object to be produced.
     */
    void produce(T object);
}