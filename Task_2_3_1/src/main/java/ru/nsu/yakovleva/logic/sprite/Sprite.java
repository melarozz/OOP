package ru.nsu.yakovleva.logic.sprite;

public interface Sprite {

    Object getBoundary();

    void update(double x, double y);

    boolean intersects(Sprite sprite);

    void render(Object object);
}
