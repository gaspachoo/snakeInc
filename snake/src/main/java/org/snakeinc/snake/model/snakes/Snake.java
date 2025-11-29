package org.snakeinc.snake.model.snakes;

import java.awt.Color;
import java.util.ArrayList;

import lombok.Getter;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.UnderfedException;
import org.snakeinc.snake.model.*;
import org.snakeinc.snake.model.fruits.Apple;
import org.snakeinc.snake.model.fruits.Broccoli;
import org.snakeinc.snake.model.fruits.Fruit;
import org.snakeinc.snake.model.states.GoodHealthState;
import org.snakeinc.snake.model.states.SnakeState;

public abstract sealed class Snake permits Anaconda, Python, BoaConstrictor {

    protected final ArrayList<Cell> body;
    protected final FruitEatenListener onFruitEatenListener;
    private final Grid grid;
    @Getter
    protected Color mainColor;
    @Getter
    protected Color skinColor;
    @Getter
    private int moveCount;
    @Getter
    private int eatCount;
    @Getter
    private int bonusCount;
    @Getter
    private SnakeState state;

    public enum Direction { U, D, R, L}

    public Snake(FruitEatenListener listener, Grid grid) {
        this.state = new GoodHealthState(this);
        this.body = new ArrayList<>();
        this.onFruitEatenListener = listener;
        this.grid = grid;
        this.eatCount = 0;
        this.bonusCount = 0;
        this.moveCount = 0;
        Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
        Cell mid = grid.getTile(GameParams.SNAKE_DEFAULT_X-1, GameParams.SNAKE_DEFAULT_Y);
        Cell tail = grid.getTile(GameParams.SNAKE_DEFAULT_X-2, GameParams.SNAKE_DEFAULT_Y);
        head.addSnake(this);
        mid.addSnake(this);
        tail.addSnake(this);
        body.add(head);
        body.add(mid);
        body.add(tail);
    }

    public int getSize() {
        return body.size();
    }

    public Cell getHead() {
        return body.getFirst();
    }

    public void eat(Fruit Fruit, Cell cell) {}

    private void updateBonusCountAndState(Cell head){
        switch (head.getFruit()){
            case Apple apple:
                if (!apple.isPoisonous()) {
                    bonusCount += 2;
                }
                else {
                    state.eatPoisonedApple();
                }
                break;
            case Broccoli broccoli:
                state.eatBroccoli();
                if (!broccoli.isSteamed()) {
                    bonusCount ++;
                }
                break;
            default :
                break;
        }
    }

    public void move(Direction direction) throws OutOfPlayException, SelfCollisionException, UnderfedException {
        moveCount++;
        int x = getHead().getX();
        int y = getHead().getY();
        switch (direction) {
            case U:
                y--;
                break;
            case D:
                y++;
                break;
            case L:
                x--;
                break;
            case R:
                x++;
                break;
        }
        Cell newHead = grid.getTile(x, y);
        if (newHead == null) {
            throw new OutOfPlayException();
        }
        if (newHead.containsASnake()) {
            throw new SelfCollisionException();
        }

        // Eat Fruit :
        if (newHead.containsAnFruit()) {
            eatCount++;
            updateBonusCountAndState(newHead);

            eat(newHead.getFruit(), newHead);
            if (getSize()==0){
                throw new UnderfedException();
            }
            System.out.println(state);
            return;
        }

        // The snake did not eat :
        newHead.addSnake(this);
        body.addFirst(newHead);

        body.getLast().removeSnake();
        body.removeLast();



    }

    public void changeState(SnakeState state){
        this.state = state;
    }

}