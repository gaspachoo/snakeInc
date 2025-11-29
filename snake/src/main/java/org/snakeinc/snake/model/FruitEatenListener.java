package org.snakeinc.snake.model;

import org.snakeinc.snake.model.fruits.Fruit;

public interface FruitEatenListener {

    void onFruitEaten(Fruit Fruit, Cell cell);

}
