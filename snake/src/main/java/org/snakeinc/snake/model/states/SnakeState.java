package org.snakeinc.snake.model.states;

import org.snakeinc.snake.model.snakes.Snake;


public abstract class SnakeState {

    protected Snake snake;
    SnakeState(Snake snake) {
        this.snake = snake;
    }
    public abstract Snake.Direction editMoves(Snake.Direction direction);
    public abstract void eatPoisonedApple();
    public abstract void eatBroccoli();
}
