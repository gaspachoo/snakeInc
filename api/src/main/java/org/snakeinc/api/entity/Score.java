package org.snakeinc.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(force = true)
public class Score {
    @Getter @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id = 1;

    @Getter
    private final int score;

    @Getter
    private final String snake;

    @Getter
    private final LocalDateTime playedAt;

    @Getter @ManyToOne
    @JoinColumn(name = "player_id")
    private final Player player;

    public Score(int score, Player player, String snake) {
        this.score = score;
        this.playedAt = LocalDateTime.now();
        this.player = player;
        this.snake = snake;
    }
}
