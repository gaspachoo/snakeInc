package org.snakeinc.api.service;


import lombok.Data;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.Score;
import org.snakeinc.api.entity.ScoreParams;
import org.snakeinc.api.repository.PlayerRepo;
import org.snakeinc.api.repository.ScoreRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service @Data
public class ScoreService {
    private final ScoreRepo scoreRepo;
    private final PlayerRepo playerRepo;

    private ScoreDto mapToScoreDto(Score score) {
        Player p = score.getPlayer();

        PlayerWithoutScoresDto playerDto = new PlayerWithoutScoresDto(
                p.getId(),
                p.getName(),
                p.getAge(),
                p.getCategory(),
                p.getCreated_at()
        );

        return new ScoreDto(
                score.getId(),
                score.getScore(),
                score.getSnake(),
                score.getPlayedAt(),
                playerDto
        );
    }

    public ScoreDto addScore(ScoreParams scoreParams) {
        Player player = playerRepo.findById(scoreParams.getPlayerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This player was not found"));
        Score score = new Score(scoreParams.getScore(), player, scoreParams.getSnake());
        return mapToScoreDto((scoreRepo.save(score)));
    }

    public List<ScoreDto> getScores(ScoreParams scoreParams) {
        return scoreRepo.findBySnakeAndPlayerId(
                scoreParams.getSnake(),
                scoreParams.getPlayerId()
        ).stream().map(this::mapToScoreDto).collect(Collectors.toList());
    }

    public ScoreDto getBestScoreBySnake(String snake) {
        Score score = scoreRepo.findBySnake(snake).stream().max(Comparator.comparing(Score::getScore)).orElse(null);
        return mapToScoreDto(score);
    }

    public record PlayerWithoutScoresDto(
            int id, String name, int age, String category, LocalDateTime createdAt
    ) {}
    public record ScoreDto(
            int id, int score, String snake, LocalDateTime playedAt, PlayerWithoutScoresDto player
    ) {}
}
