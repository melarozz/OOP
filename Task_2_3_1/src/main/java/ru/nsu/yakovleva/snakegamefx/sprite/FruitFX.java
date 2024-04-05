package ru.nsu.yakovleva.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;

/**
 * Represents the visual representation of a fruit in a JavaFX environment.
 */
public class FruitFX extends Fruit {
    private Skin skin;

    /**
     * Constructs a FruitFX object with the specified width and height.
     *
     * @param width  The width of the fruit.
     * @param height The height of the fruit.
     */
    public FruitFX(double width, double height) {
        super(width, height);
    }

    /**
     * Sets the skin for the fruit, which includes the image representation.
     *
     * @param skin The skin to set for the fruit.
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderFruit(Cell fruit, ImageView imageView) {
        imageView.setX(fruit.getX());
        imageView.setY(fruit.getY());
        return imageView;
    }

    /**
     * Renders the fruit onto the specified frame.
     *
     * @param object The frame to render the fruit on.
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderFruit(getBoundary(), skin.getImage()));
        frame.getChildren().add(fruit);
    }
}
