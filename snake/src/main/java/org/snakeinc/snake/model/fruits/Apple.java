package org.snakeinc.snake.model.fruits;
import lombok.Getter;
import java.awt.Color;

public final class Apple extends Fruit {

    @Getter
    private final boolean poisonous;

    public Apple(boolean poisonous) {
        super();
        this.poisonous = poisonous;
        if (poisonous) {
            this.mainColor = new Color(110, 47, 149, 100);
        }
        else {
            this.mainColor = Color.RED;
        }
    }
}
