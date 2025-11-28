package org.snakeinc.snake.model.states;

import org.snakeinc.snake.model.Snake;

public class GoodHealthState extends SnakeState {
    public GoodHealthState(Snake snake) {
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
