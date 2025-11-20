package org.snakeinc.snake.model;

import lombok.Getter;

import java.awt.Color;

@Getter
public abstract sealed class Fruit permits Apple, Broccoli{
    @Getter
    protected Color mainColor;


    public Fruit() {
        this.mainColor = Color.WHITE;
    }

}
