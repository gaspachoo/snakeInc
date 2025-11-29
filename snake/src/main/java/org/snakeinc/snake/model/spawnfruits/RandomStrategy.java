package org.snakeinc.snake.model.spawnfruits;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;

import java.util.Random;

public class RandomStrategy implements SpawnStrategy {

    @Override
    public Cell spawnFruit(Cell cell, Grid grid) {
        var random = new Random();
        cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
        while (cell.containsASnake() | cell.containsAnFruit()){
            cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
        }
        return cell;
    }
}
