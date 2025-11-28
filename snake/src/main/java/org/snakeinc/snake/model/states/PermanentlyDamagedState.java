package org.snakeinc.snake.model.states;

import org.snakeinc.snake.model.snakes.Snake;

public class PermanentlyDamagedState extends SnakeState {
    public PermanentlyDamagedState(Snake snake) {
        super(snake);
    }

    @Override
    public Snake.Direction editMoves(Snake.Direction direction){
        switch (direction) {
            case U -> direction = Snake.Direction.D;
            case D -> direction = Snake.Direction.U;
            case L -> direction = Snake.Direction.R;
            case R -> direction = Snake.Direction.L;
        }
        return direction;
    }

    @Override
    public void eatPoisonedApple() {
        //nothing
    }

    @Override
    public void eatBroccoli() {
        //nothing
    }
}
