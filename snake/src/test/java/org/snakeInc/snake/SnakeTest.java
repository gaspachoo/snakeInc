package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.UnderfedException;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.snakes.Anaconda;
import org.snakeinc.snake.model.snakes.Snake;
import org.snakeinc.snake.model.spawnfruits.Basket;
import org.snakeinc.snake.model.spawnfruits.RandomStrategy;

public class SnakeTest {

    Game game = new Game();

    @Test
    public void anacondaEatsApple_GrowsBodySize() throws OutOfPlayException, SelfCollisionException, UnderfedException {
        Grid grid = new Grid();
        Basket basket = new Basket(grid, new RandomStrategy());
        Anaconda anaconda = new Anaconda(basket::removeFruitInCell, grid);
        basket.setSnake(anaconda);
        
        int initialSize = anaconda.getSize();
        Cell target = grid.getTile(5, 4);
        basket.addFruit(target, true, false); // Normal apple
        
        anaconda.move(Snake.Direction.U);
        
        Assertions.assertEquals(initialSize + 1, anaconda.getSize());
    }

    @Test
    void snakeMovesUp_ReturnCorrectHead() throws OutOfPlayException, SelfCollisionException, UnderfedException {
        game.getSnake().move(Snake.Direction.U);
        Assertions.assertEquals(5, game.getSnake().getHead().getX());
        Assertions.assertEquals(4, game.getSnake().getHead().getY());
    }

    @Test
    void snakeOutOfPLayBehavior() throws OutOfPlayException, SelfCollisionException, UnderfedException {
        for (int i = 0; i < GameParams.SNAKE_DEFAULT_Y; i++) {
            game.getSnake().move(Snake.Direction.U);
        }
        Assertions.assertThrows(OutOfPlayException.class, () -> game.getSnake().move(Snake.Direction.U));
    }

    @Test
    void snakeSelfCollisionBehavior() throws OutOfPlayException, SelfCollisionException, UnderfedException {
        for (int i = 0; i < 5; i++) {
            game.getBasket().addFruit(game.getGrid().getTile(6+i,5));
            game.getSnake().move(Snake.Direction.R);
        }
        game.getSnake().move(Snake.Direction.D);
        game.getSnake().move(Snake.Direction.L);
        Assertions.assertThrows(SelfCollisionException.class, () -> game.getSnake().move(Snake.Direction.U));
    }

}