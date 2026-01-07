package org.snakeinc.api.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.snakeinc.api.validation.ExistingPlayer;

@Data
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

}
