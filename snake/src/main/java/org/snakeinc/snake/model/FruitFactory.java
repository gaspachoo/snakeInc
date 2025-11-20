package org.snakeinc.snake.model;

import java.util.Random;

public class FruitFactory {

    public static Fruit createFruitInCell(Cell cell) {
        var random = new Random();
        var prob = random.nextInt(0,3);
        Fruit fruit;
        if (prob == 0) {
            fruit = new Broccoli();
        } else {
            fruit = new Apple();
        }


        cell.addFruit(fruit);
        return fruit;
    }
}