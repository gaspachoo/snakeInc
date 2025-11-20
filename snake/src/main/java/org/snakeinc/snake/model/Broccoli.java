package org.snakeinc.snake.model;
import lombok.Getter;
import java.awt.Color;

public final class Broccoli extends Fruit {

    @Getter
    private final boolean steamed;

    public Broccoli(boolean steamed) {
        super();
        this.steamed = steamed;
        if (steamed) {
            this.mainColor = new Color(110, 47, 149, 100);
        }
        else {
            this.mainColor = Color.GREEN;
        }

    }

}
