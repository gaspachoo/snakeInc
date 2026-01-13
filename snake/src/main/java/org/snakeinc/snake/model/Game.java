package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.UnderfedException;
import org.snakeinc.snake.model.snakes.Anaconda;
import org.snakeinc.snake.model.snakes.BoaConstrictor;
import org.snakeinc.snake.model.snakes.Python;
import org.snakeinc.snake.model.snakes.Snake;
import org.snakeinc.snake.model.spawnfruits.*;
import java.util.Random;

@Getter
public class Game {

    private final Grid grid;
    private final Basket basket;
    private final Snake snake;
    private final SpawnStrategy strategy;
    private final String playerName;

    public Game(String playerName) {
        grid = new Grid();
        this.playerName = playerName;

        var random = new Random();
        int strategy_rand = random.nextInt(0, 3);
        if (strategy_rand == 0) {
            strategy = new RandomStrategy();
        } else if (strategy_rand == 1) {
            strategy = new EasyStrategy();
        } else {
            strategy = new DifficultStrategy();
        }
        System.out.println(strategy);
        basket = new Basket(grid, strategy);

        int snake_rand = random.nextInt(0, 10);
        if (snake_rand == 0) {
            snake = new BoaConstrictor(basket::removeFruitInCell, grid);
        } else if (snake_rand == 1) {
            snake = new Python(basket::removeFruitInCell, grid);
        } else {
            snake = new Anaconda(basket::removeFruitInCell, grid);
        }

        basket.setSnake(snake);
        basket.refillIfNeeded(3);
    }

    public void iterate(Snake.Direction direction)
            throws OutOfPlayException, SelfCollisionException, UnderfedException {
        snake.move(direction);
        basket.refillIfNeeded(3);
    }
}