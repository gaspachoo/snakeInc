package org.snakeinc.snake.model.spawnfruits;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;

import java.util.Random;

public class RandomStrategy implements SpawnStrategy {

    @Override
    public Cell spawnFruit(Grid grid, Snake snake) {
        var random = new Random();
        Cell cell;
        do {
            cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
        } while (cell.containsASnake() | cell.containsAnFruit());
        return cell;
    }
}
