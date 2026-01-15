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

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service @Data
public class ScoreService {
    private final ScoreRepo scoreRepo;
    private final PlayerRepo playerRepo;

    public Score addScore(ScoreParams scoreParams) {
        Player player = playerRepo.findById(scoreParams.getPlayerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This player was not found"));
        Score score = new Score(scoreParams.getScore(), player, scoreParams.getSnake());
        return scoreRepo.save(score);
    }

    public List<Score> getScores(ScoreParams scoreParams) {
        return scoreRepo.findBySnakeAndPlayer_Id(
                scoreParams.getSnake(),
                scoreParams.getPlayerId()
        );
    }

    public List<StatsItem> getStats(int playerId) {
        return scoreRepo.findByPlayer_Id(playerId).stream()
                .collect(Collectors.groupingBy(
                        Score::getSnake, Collectors.summarizingDouble(Score::getScore))).
                entrySet().stream()
                .map(e -> new StatsItem(e.getKey(), (int) e.getValue().getMin(), (int) e.getValue().getMax(), e.getValue().getAverage()))
                .toList();
    }
    public record StatsItem(String snake, int min, int max, double average) {}

    public Score getBestScoreBySnake(String snake) {
        return scoreRepo.findBySnake(snake).stream().max(Comparator.comparing(Score::getScore)).orElseThrow(NoSuchElementException::new);
    }
}
