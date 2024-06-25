package ru.nsu.yakovleva.logic.sprite;

/**
 * Represents a game object in the application.
 */
public interface Sprite {

    /**
     * Gets the boundary of the sprite.
     *
     * @return The boundary of the sprite.
     */
    Object getBoundary();

    /**
     * Updates the sprite's position or state.
     *
     * @param x The change in the x-coordinate.
     * @param y The change in the y-coordinate.
     */
    void update(double x, double y);

    /**
     * Checks if the sprite intersects with another sprite.
     *
     * @param sprite The sprite to check intersection with.
     * @return True if the sprites intersect, false otherwise.
     */
    boolean intersects(Sprite sprite);

    /**
     * Renders the sprite.
     *
     * @param object The object to render the sprite on.
     */
    void render(Object object);
}