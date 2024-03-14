package ru.nsu.yakovleva.order;

/**
 * Represents the state of an order.
 */
public enum State {

    // Order is in the queue
    IN_QUEUE("in queue"),

    // Order is being cooked
    COOKING("cooking"),

    // Order is in stock
    IN_STOCK("in stock"),

    // Order is being delivered
    DELIVERING("delivering"),

    // Order has been delivered
    DELIVERED("delivered");

    // String representation of the state
    public final String state;

    /**
     * Constructs a new State enum value with the given state string.
     *
     * @param state The string representation of the state.
     */
    State(String state) {
        this.state = state;
    }

    /**
     * Retrieves the string representation of the state.
     *
     * @return The state string.
     */
    public String getState() {
        return state;
    }
}