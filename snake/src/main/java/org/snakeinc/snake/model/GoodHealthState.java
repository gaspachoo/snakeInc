package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.UnderfedException;

public class GoodHealthState extends SnakeState {
    GoodHealthState(Snake snake) {
        super(snake);
    }
    @Override
    public Snake.Direction editMoves(Snake.Direction direction){
        return direction;
    }

    @Override
    public void eatPoisonedApple() {
        if (snake != null) {
            snake.changeState(new PoisonedState(snake));
        }
    }
    @Override
    public void eatBroccoli() {
        //nothing
    }

}
