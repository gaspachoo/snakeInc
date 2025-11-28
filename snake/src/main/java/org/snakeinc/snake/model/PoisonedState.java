package org.snakeinc.snake.model;

public class PoisonedState extends SnakeState {
    PoisonedState(Snake snake) {
        super(snake);
    }

    @Override
    public Snake.Direction editMoves(Snake.Direction direction){
        return direction;
    }

    @Override
    public void eatPoisonedApple() {
        if(snake != null) {snake.changeState(new PermanentlyDamagedState(snake));}
    }

    @Override
    public void eatBroccoli() {
        if(snake != null) {snake.changeState(new GoodHealthState(snake));};
    }
}
