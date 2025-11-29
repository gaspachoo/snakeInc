package org.snakeinc.snake.model.spawnfruits;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;
import java.util.Random;

public class DifficultStrategy implements SpawnStrategy {

    @Override
    public Cell spawnFruit(Grid grid, Snake snake) {
        var random = new Random();
        var LeftRightBin = random.nextInt(2);
        var TopBottomBin = random.nextInt(2);
        Cell cell;
        do {
            cell = grid.getTile(
                    (LeftRightBin * random.nextInt(0, 3)
                            + (1-LeftRightBin) * random.nextInt(GameParams.TILES_X-3, GameParams.TILES_X)),
                    (TopBottomBin * random.nextInt(0, 3)
                            + (1-TopBottomBin) * random.nextInt(GameParams.TILES_Y-3, GameParams.TILES_Y)));
        } while (cell.containsASnake() || cell.containsAnFruit());
        return cell;
    }
}
