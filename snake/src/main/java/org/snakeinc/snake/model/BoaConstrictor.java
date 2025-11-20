package org.snakeinc.snake.model;

public final class BoaConstrictor extends Snake{
    public BoaConstrictor(FruitEatenListener listener, Grid grid){
        super(listener, grid);
    }

    public void eat(Fruit Fruit, Cell cell) {
        switch (cell.getFruit()) {
            case Apple apple:
                body.getLast().removeSnake();
                body.removeLast();
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
            case Broccoli broccoli:
                onFruitEatenListener.onFruitEaten(Fruit, cell);
                break;
        }
    }
}