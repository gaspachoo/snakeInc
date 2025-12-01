package org.snakeinc.snake.model.fruits;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;

public class FruitFactory {

    public static Fruit createFruitInCell(Cell cell, Grid grid, boolean isApple, boolean isAbnormal) {
        Fruit fruit;
        if (!isApple) {
            fruit = new Broccoli(isAbnormal);
        } else {
            fruit = new Apple(isAbnormal);
        }

        fruit.setCurrentCell(cell);
        fruit.setGrid(grid);
        cell.addFruit(fruit);
        return fruit;
    }
}