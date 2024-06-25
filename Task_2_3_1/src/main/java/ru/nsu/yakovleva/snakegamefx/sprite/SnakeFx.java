package ru.nsu.yakovleva.snakegamefx.sprite;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.yakovleva.logic.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.snake.Direction;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;


/**
 * Represents the visual representation of a snake in a JavaFX environment.
 */
public class SnakeFx extends Snake {
    private Skin headSkin;
    private Skin rotatedSkin;
    private Skin straightSkin;
    private Skin tailSkin;

    /**
     * Constructs a SnakeFX object with the specified width and height.
     *
     * @param width  The width of the snake.
     * @param height The height of the snake.
     */
    public SnakeFx(double width, double height) {
        super(width, height);
    }

    /**
     * Sets the skins for different parts of the snake.
     *
     * @param headSkin     The skin for the snake's head.
     * @param rotatedSkin  The skin for the rotated parts of the snake.
     * @param straightSkin The skin for the straight parts of the snake.
     * @param tailSkin     The skin for the snake's tail.
     */
    public void setSkins(Skin headSkin, Skin rotatedSkin, Skin straightSkin, Skin tailSkin) {
        this.headSkin = headSkin;
        this.rotatedSkin = rotatedSkin;
        this.straightSkin = straightSkin;
        this.tailSkin = tailSkin;
    }

    private ImageView renderCell(Cell cell, ImageView imageView) {
        imageView.setX(cell.getX());
        imageView.setY(cell.getY());
        return imageView;
    }

    private ImageView renderHead(Cell head, Direction direction) {
        ImageView imageView = switch (direction) {
            case LEFT -> headSkin.getRotatedImage(180);
            case UP -> headSkin.getRotatedImage(270);
            case DOWN -> headSkin.getRotatedImage(90);
            case RIGHT -> headSkin.getImage();
        };
        return renderCell(head, imageView);
    }

    private ImageView renderTail(Cell tail, Cell previous) {
        if (tail.getY() == previous.getY()) {
            if (tail.getX() < previous.getX()) {
                return renderCell(tail, tailSkin.getImage());
            }
            if (tail.getX() > previous.getX()) {
                return renderCell(tail, tailSkin.getRotatedImage(180));
            }
        }
        if (tail.getY() > previous.getY()) {
            return renderCell(tail, tailSkin.getRotatedImage(270));
        }
        return renderCell(tail, tailSkin.getRotatedImage(90));
    }

    private ImageView renderFlake(Cell flake, Cell previous, Cell next) {
        if (next.getY() == previous.getY()) {
            return renderCell(flake, straightSkin.getImage());
        }
        if (flake.getX() == previous.getX()) {
            if (next.getY() < previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedSkin.getImage());
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(270));
                }
            }
            if (next.getY() > previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(90));
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(180));
                }
            }
        }
        if (flake.getX() == next.getX()) {
            if (next.getY() < previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(180));
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(90));
                }
            }
            if (next.getY() > previous.getY()) {
                if (next.getX() < previous.getX()) {
                    return renderCell(flake, rotatedSkin.getRotatedImage(270));
                }
                if (next.getX() > previous.getX()) {
                    return renderCell(flake, rotatedSkin.getImage());
                }
            }
        }
        return renderCell(flake, straightSkin.getRotatedImage(90));
    }

    private List<ImageView> renderBody(List<Cell> boundary) {
        List<ImageView> body = new ArrayList<>();
        body.add(renderHead(boundary.get(0), getDirection()));
        for (int i = 1; i < boundary.size() - 1; ++i) {
            body.add(renderFlake(boundary.get(i), boundary.get(i + 1), boundary.get(i - 1)));
        }
        body.add(renderTail(boundary.get(boundary.size() - 1), boundary.get(boundary.size() - 2)));
        return body;
    }

    /**
     * Renders the snake on the screen.
     *
     * @param object The object representing the screen.
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group snake = new Group();
        snake.getChildren().addAll(renderBody(getBoundary()));
        frame.getChildren().add(snake);
    }
}
