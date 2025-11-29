package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.*;

import java.awt.Color;

public final class BoaConstrictor extends Snake {
    public BoaConstrictor(FruitEatenListener listener, Grid grid){
        super(listener, grid);
        this.mainColor = Color.BLUE;
        this.skinColor = Color.BLUE.darker();
    }

    public void eat(Fruit Fruit, Cell cell) {
        switch (cell.getFruit()) {
            case Apple _ :
                body.getLast().removeSnake();
                body.removeLast();
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
            case Broccoli _ :
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
        }
    }
}