package org.snakeinc.snake.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
    private int score;
    private LocalDateTime playedAt;
    private PlayerDTO playerDTO;

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = playedAt.format(formatter);
        return score + ", on " + formattedDate + ", by " + playerDTO;
    }
}
