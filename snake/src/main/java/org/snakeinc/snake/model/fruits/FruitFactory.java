package org.snakeinc.snake.model.fruits;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;

import java.util.Random;

public class FruitFactory {

    public static Fruit createFruitInCell(Cell cell, Grid grid) {
        var random = new Random();
        var probFruit = random.nextInt(0, 4);
        var probAbnormal = random.nextInt(0, 4);
        Fruit fruit;
        if (probFruit == 0) {
            fruit = new Broccoli(probAbnormal == 0);
        } else {
            fruit = new Apple(probAbnormal == 0);
        }

        fruit.setCurrentCell(cell);
        fruit.setGrid(grid);
        cell.addFruit(fruit);
        return fruit;
    }
}