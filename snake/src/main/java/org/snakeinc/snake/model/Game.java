package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.UnderfedException;
import org.snakeinc.snake.model.snakes.Anaconda;
import org.snakeinc.snake.model.snakes.BoaConstrictor;
import org.snakeinc.snake.model.snakes.Python;
import org.snakeinc.snake.model.snakes.Snake;

import java.util.Random;

@Getter
public class Game {

    private final Grid grid;
    private final Basket basket;
    private final Snake snake;

    public Game() {
        grid = new Grid();
        basket = new Basket(grid);
        basket.refillIfNeeded(3);
        var random = new Random();
        int type = random.nextInt(0,10);
        if (type == 0) {
            snake = new BoaConstrictor(basket::removeFruitInCell, grid);
        } else if (type == 1) {
            snake = new Python(basket::removeFruitInCell, grid);
        }
        else {snake = new Anaconda(basket::removeFruitInCell, grid);
        }
    }

    public void iterate(Snake.Direction direction) throws OutOfPlayException, SelfCollisionException, UnderfedException {
        snake.move(direction);
        basket.refillIfNeeded(3);
    }


}