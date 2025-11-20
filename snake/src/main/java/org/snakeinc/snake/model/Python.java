package org.snakeinc.snake.model;

public final class Python extends Snake{
    public Python(FruitEatenListener listener, Grid grid){
        super(listener, grid);
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