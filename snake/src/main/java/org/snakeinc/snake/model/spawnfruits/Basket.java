package org.snakeinc.snake.model.spawnfruits;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.fruits.Fruit;
import org.snakeinc.snake.model.fruits.FruitFactory;

@Data
public class Basket {

    private Grid grid;
    private List<Fruit> Fruits;

    public Basket(Grid grid) {
        Fruits = new ArrayList<>();
        this.grid = grid;
    }

    public void addFruit(Cell cell) {
        SpawnStrategy strategy = new RandomStrategy();
        if (cell == null) {
            cell = strategy.spawnFruit(cell, grid);
        }
        Fruit Fruit = FruitFactory.createFruitInCell(cell);
        Fruits.add(Fruit);
    }

    public void removeFruitInCell(Fruit Fruit, Cell cell) {
        cell.removeFruit();
        Fruits.remove(Fruit);
    }

    public boolean isEmpty() {
        return Fruits.isEmpty();
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

}