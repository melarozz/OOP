package ru.nsu.yakovleva.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.yakovleva.logic.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.board.Board;


/**
 * Represents the visual representation of the game board in a JavaFX environment.
 */
public class BoardFx extends Board {
    private Skin skin;

    /**
     * Constructs a BoardFX object with the specified width and height.
     *
     * @param width  The width of the board.
     * @param height The height of the board.
     */
    public BoardFx(double width, double height) {
        super(width, height);
    }

    /**
     * Sets the skin for the board, which includes the image representation.
     *
     * @param skin The skin to set for the board.
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderBoard(Cell board, ImageView imageView) {
        imageView.setX(board.getX());
        imageView.setY(board.getY());
        return imageView;
    }

    /**
     * Renders the board onto the specified frame.
     *
     * @param object The frame to render the board on.
     */
    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group board = new Group();
        board.getChildren().add(renderBoard(getBoundary(), skin.getImage()));
        frame.getChildren().add(board);
    }
}
