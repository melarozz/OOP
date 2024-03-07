package ru.nsu.yakovleva.order;

public class Order {
    private final int id;
    private State state;

    public Order(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[Order #" + id + "], [" + state.getState() + "]";
    }
}
