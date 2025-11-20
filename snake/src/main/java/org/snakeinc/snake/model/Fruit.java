package org.snakeinc.snake.model;

import lombok.Getter;

@Getter
public abstract sealed class Fruit permits Apple, Broccoli{
    public Fruit() {
    }

}
