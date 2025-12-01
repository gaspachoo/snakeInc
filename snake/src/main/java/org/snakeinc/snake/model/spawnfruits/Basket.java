package org.snakeinc.snake.model.spawnfruits;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Data;
import lombok.Setter;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.fruits.Apple;
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
        var random = new Random();
        boolean isApple = (random.nextInt(0, 4) != 0);
        boolean isAbnormal = (random.nextInt(0, 4) == 0);

        Fruit fruit = FruitFactory.createFruitInCell(cell, grid,  isApple, isAbnormal);
        Fruits.add(fruit);

        if (snake != null) {
            snake.attachObserver(fruit);
        }
    }

    public void addFruit(Cell cell, boolean isApple, boolean isAbnormal) {
        if (cell == null) {
            cell = strategy.spawnFruit(grid, snake);
        }
        Fruit fruit = FruitFactory.createFruitInCell(cell, grid, isApple, isAbnormal);
        Fruits.add(fruit);

        if (snake != null) {
            snake.attachObserver(fruit);
        }
    }

    public void removeFruitInCell(Fruit fruit, Cell cell) {
        cell.removeFruit();
        Fruits.remove(fruit);

        if (snake != null) {
            snake.detachObserver(fruit);
        }
    }

    private void refill(int nFruits) {
        for (int i = 0; i < nFruits; i++) {
            addFruit(null);
        }
    }

    public void refillIfNeeded(int nFruits) {
        boolean hasApple = Fruits.stream().anyMatch(fruit -> fruit instanceof Apple);
        if (!hasApple){
            addFruit(null, true, false);
        }
        int missingFruit = nFruits - Fruits.size();
        if (missingFruit > 1) {
            refill(missingFruit);
        }
    }


}