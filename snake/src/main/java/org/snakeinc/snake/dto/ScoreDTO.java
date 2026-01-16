package org.snakeinc.snake.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
    private int id;
    private int score;
    private String snake;
    private LocalDateTime playedAt;
    @JsonProperty("player")
    private PlayerWithoutScoresDTO playerDTO;

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = playedAt.format(formatter);
        return score + ", on " + formattedDate + ", by " + playerDTO;
    }
}
