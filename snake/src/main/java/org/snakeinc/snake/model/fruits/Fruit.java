package org.snakeinc.snake.model.fruits;

import lombok.Getter;
import lombok.Setter;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.SnakeObserver;

import java.awt.Color;
import java.util.Random;

@Getter
public abstract sealed class Fruit implements SnakeObserver permits Apple, Broccoli {
    protected Color mainColor;
    @Setter
    private Cell currentCell;
    @Setter
    private Grid grid;

    public Fruit() {
    }

    @Override
    public void onSnakeMove(Cell headCell) {
        Random random = new Random();

        if (calculateManhattanDistance(headCell) < 5 && random.nextDouble() < 0.1) {
            tryEscape(headCell);
        }
    }

    private int calculateManhattanDistance(Cell snakeHead) {
        return Math.abs(currentCell.getX() - snakeHead.getX()) +
                Math.abs(currentCell.getY() - snakeHead.getY());
    }

    private void tryEscape(Cell snakeHead) {
        int dx = currentCell.getX() - snakeHead.getX();
        int dy = currentCell.getY() - snakeHead.getY();

        int newX = currentCell.getX() + Integer.compare(dx, 0);
        int newY = currentCell.getY() + Integer.compare(dy, 0);

        Cell newCell = grid.getTile(newX, newY);

        if (newCell != null && !newCell.containsASnake() && !newCell.containsAnFruit()) {
            currentCell.removeFruit();
            newCell.addFruit(this);
            this.currentCell = newCell;
        }
    }
}
