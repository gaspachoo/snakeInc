package org.snakeinc.snake.model.spawnfruits;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;

public interface SpawnStrategy {
    Cell spawnFruit(Cell cell, Grid grid);
}
