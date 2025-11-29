package org.snakeinc.snake.model.spawnfruits;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Setter;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.fruits.Apple;
import org.snakeinc.snake.model.fruits.Broccoli;
import org.snakeinc.snake.model.fruits.Fruit;
import org.snakeinc.snake.model.fruits.FruitFactory;
import org.snakeinc.snake.model.snakes.Snake;

@Data
public class Basket {

    private Grid grid;
    private List<Fruit> Fruits;
    private SpawnStrategy strategy;
    @Setter
    private Snake snake;

    public Basket(Grid grid, SpawnStrategy strategy) {
        Fruits = new ArrayList<>();
        this.grid = grid;
        this.strategy = strategy;
    }

    public void addFruit(Cell cell) {
        if (cell == null) {
            cell = strategy.spawnFruit(grid, snake);
        }
        Fruit Fruit = FruitFactory.createFruitInCell(cell);
        Fruits.add(Fruit);
    }

    public void removeFruitInCell(Fruit Fruit, Cell cell) {
        cell.removeFruit();
        Fruits.remove(Fruit);
    }

    private void refill(int nFruits) {
        for (int i = 0; i < nFruits; i++) {
            addFruit(null);
        }
    }

    public void refillIfNeeded(int nFruits) {
        int missingFruit = nFruits - Fruits.size();
        if (missingFruit > 0) {
            refill(missingFruit);
        }
    }

    public void addSpecificFruit(Cell cell, boolean isApple, boolean isAbnormal) {
        Fruit fruit;
        if (isApple) {
            fruit = new Apple(isAbnormal);
        } else {
            fruit = new Broccoli(isAbnormal);
        }
        cell.addFruit(fruit);
        Fruits.add(fruit);
    }

}