package org.snakeinc.snake.model;

public class PermanentlyDamagedState extends SnakeState{
    PermanentlyDamagedState(Snake snake) {
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
