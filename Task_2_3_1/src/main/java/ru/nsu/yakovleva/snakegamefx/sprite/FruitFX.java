package ru.nsu.yakovleva.snakegamefx.sprite;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;

public class FruitFX extends Fruit {
    private Skin skin;

    public FruitFX(double width, double height) {
        super(width, height);
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    private ImageView renderFruit(Cell fruit, ImageView imageView) {
        imageView.setX(fruit.getX());
        imageView.setY(fruit.getY());
        return imageView;
    }

    @Override
    public void render(Object object) {
        Group frame = ((Group) object);
        Group fruit = new Group();
        fruit.getChildren().add(renderFruit(getBoundary(), skin.getImage()));
        frame.getChildren().add(fruit);
    }
}
