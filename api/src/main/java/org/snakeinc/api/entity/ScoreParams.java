package org.snakeinc.api.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.snakeinc.api.validation.ExistingPlayer;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreParams {

    @Positive
    private int score;

    @Positive
    @ExistingPlayer
    private int playerId;

    @Pattern(regexp = "^(boaConstrictor|anaconda|python)$",
            message = "snake must be boaConstrictor, anaconda, or python")
    private String snake;

    private LocalDateTime playedAt;

    public ScoreParams(int playerId, String snake) {
        this.playerId = playerId;
        this.snake = snake;
    }
}
