package ru.nsu.yakovleva.snakegamefx.sprite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the visual skin used for sprites in a JavaFX environment.
 */
public class Skin {
    private final double width;
    private final double height;
    private final Image image;

    /**
     * Constructs a Skin object with the specified width, height, and image.
     *
     * @param width  The width of the skin.
     * @param height The height of the skin.
     * @param image  The image representing the skin.
     */
    public Skin(double width, double height, Image image) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

    /**
     * Gets the image view of the skin.
     *
     * @return The image view representing the skin.
     */
    public ImageView getImage() {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.preserveRatioProperty();
        return imageView;
    }

    /**
     * Gets the rotated image view of the skin with the specified rotation in degrees.
     *
     * @param degrees The rotation angle in degrees.
     * @return The rotated image view representing the skin.
     */
    public ImageView getRotatedImage(double degrees) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setRotate(degrees);
        imageView.preserveRatioProperty();
        return imageView;
    }
}
