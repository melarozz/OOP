package ru.nsu.yakovleva.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.board.Board;

public class BoardFX extends Board {
    private Skin skin;

    public BoardFX(double width, double height) {
        super(width, height);
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderBoard(Cell board, ImageView imageView) {
        imageView.setX(board.getX());
        imageView.setY(board.getY());
        return imageView;
    }

    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group board = new Group();
        board.getChildren().add(renderBoard(getBoundary(), skin.getImage()));
        frame.getChildren().add(board);
    }
}
