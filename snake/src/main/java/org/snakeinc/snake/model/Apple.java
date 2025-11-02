package org.snakeinc.snake.model;

import java.util.Random;
import lombok.Getter;

@Getter
public class Apple implements GameObject {

    private final Tile tile;

    public Apple() {
        var random = new Random();
        tile = Grid.getInstance().getTile(random.nextInt(0, Grid.TILES_X), random.nextInt(0, Grid.TILES_Y));
        tile.gameObjectsInTile.add(this);
    }

}
