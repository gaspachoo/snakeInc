package org.snakeinc.snake.model;

public interface SnakeObserver {
    void onSnakeMove(Cell headCell);
}
