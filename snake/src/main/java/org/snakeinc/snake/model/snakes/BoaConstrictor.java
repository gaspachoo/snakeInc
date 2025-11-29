package org.snakeinc.snake.model.snakes;

import org.snakeinc.snake.model.*;
import org.snakeinc.snake.model.fruits.Apple;
import org.snakeinc.snake.model.fruits.Broccoli;
import org.snakeinc.snake.model.fruits.Fruit;

import java.awt.Color;

public final class BoaConstrictor extends Snake {
    public BoaConstrictor(FruitEatenListener listener, Grid grid){
        super(listener, grid);
        this.mainColor = Color.BLUE;
        this.skinColor = Color.BLUE.darker();
    }

    public void eat(Fruit Fruit, Cell cell) {
        switch (cell.getFruit()) {
            case Apple apple :
                body.getLast().removeSnake();
                body.removeLast();
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
            case Broccoli broccoli :
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
        }
    }
}