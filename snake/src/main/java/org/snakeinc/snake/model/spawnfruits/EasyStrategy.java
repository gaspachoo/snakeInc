package org.snakeinc.snake.model.spawnfruits;

import lombok.ToString;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Snake;
import java.util.Random;

@ToString
public class EasyStrategy implements SpawnStrategy {

    @Override
    public Cell spawnFruit(Grid grid, Snake snake) {
        Cell head = snake.getHead();
        var random = new Random();
        Cell cell;
        do {
            cell = grid.getTile(
                    random.nextInt(Math.max(0, head.getX()-3),Math.min(head.getX()+3, GameParams.TILES_X)),
                    random.nextInt(Math.max(0, head.getY()-3),Math.min(head.getY()+3, GameParams.TILES_Y)));
        } while (cell.containsASnake() || cell.containsAnFruit());
        return cell;
    }
}
