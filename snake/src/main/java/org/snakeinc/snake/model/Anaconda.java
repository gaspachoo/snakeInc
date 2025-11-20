package org.snakeinc.snake.model;

public final class Anaconda extends Snake {

    public Anaconda(FruitEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Fruit Fruit, Cell cell) {
        switch (cell.getFruit()){
            case Apple apple:
                body.addFirst(cell);
                cell.addSnake(this);
                onFruitEatenListener.onFruitEaten(Fruit,cell);
                break;
            case Broccoli broccoli:
                for (int j=0; j<2; j++){
                body.getLast().removeSnake();
                body.removeLast();
            }
                onFruitEatenListener.onFruitEaten(Fruit,cell);
                break;
        }

    }
}