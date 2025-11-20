package org.snakeinc.snake.model;

import java.util.Random;

public class FruitFactory {

    public static Fruit createFruitInCell(Cell cell) {
        var random = new Random();
        var probFruit = random.nextInt(0,3);
        var probAbnormal = random.nextInt(0,5);
        Fruit fruit;
        if (probFruit == 0) {
            fruit = new Broccoli(probAbnormal == 0);
        } else {
            fruit = new Apple(probAbnormal == 0);
        }


        cell.addFruit(fruit);
        return fruit;
    }
}