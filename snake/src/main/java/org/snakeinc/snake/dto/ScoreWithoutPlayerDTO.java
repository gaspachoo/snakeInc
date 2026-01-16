package org.snakeinc.snake.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreWithoutPlayerDTO {
    private int score;
    private LocalDateTime playedAt;
}
