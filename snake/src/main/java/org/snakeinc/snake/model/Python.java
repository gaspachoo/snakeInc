package org.snakeinc.snake.model;

import java.awt.Color;

public final class Python extends Snake{
    public Python(FruitEatenListener listener, Grid grid){
        super(listener, grid);
        this.mainColor = Color.GREEN;
        this.skinColor = Color.GREEN.darker();
    }

    public void eat(Fruit Fruit, Cell cell) {
        switch (cell.getFruit()) {
            case Apple apple:
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
            case Broccoli broccoli:
                for (int i=0; i<3; i++){
                    body.getLast().removeSnake();
                    body.removeLast();
                }
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
        }
    }
}