package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.UnderfedException;

public abstract class SnakeState {

    Snake snake;
    SnakeState(Snake snake) {
        this.snake = snake;
    }
    public abstract Snake.Direction editMoves(Snake.Direction direction);
    public abstract void eatPoisonedApple();
    public abstract void eatBroccoli();
}
