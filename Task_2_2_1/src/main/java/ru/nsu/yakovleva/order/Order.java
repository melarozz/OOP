package ru.nsu.yakovleva.order;

/**
 * Represents an order in the system.
 */
public class Order {

    // Order's unique identifier
    private final int id;

    // Current state of the order
    private State state;

    /**
     * Constructs a new Order object with the given unique identifier.
     *
     * @param id The unique identifier of the order.
     */
    public Order(int id) {
        this.id = id;
    }

    /**
     * Retrieves the current state of the order.
     *
     * @return The state of the order.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the order.
     *
     * @param state The new state of the order.
     */
    public void setState(State state) {
        this.state = state;
        System.out.println(this);
    }

    /**
     * Returns a string representation of the order.
     *
     * @return A string representation containing the order's ID and state.
     */
    @Override
    public String toString() {
        return "[Order #" + id + "], [" + state.getState() + "]";
    }
}