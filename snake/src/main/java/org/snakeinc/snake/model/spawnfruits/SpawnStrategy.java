package org.snakeinc.snake.model.spawnfruits;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;

public interface SpawnStrategy {
    Cell spawnFruit(Grid grid, Snake snake);
}
