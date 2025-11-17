package org.snakeinc.snake.model;

public class FruitFactory {

    public static Fruit createFruitInCell(Cell cell) {
        Fruit fruit = new Apple();
        cell.addFruit(fruit);
        return fruit;
    }
}