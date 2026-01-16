package org.snakeinc.api.service;

import lombok.Data;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.snakeinc.api.entity.Score;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.snakeinc.api.repository.PlayerRepo;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service @Data
public class PlayerService {
    private final PlayerRepo playerRepo;

    private PlayerDto mapToPlayerDto(Player player){
        List<ScoreWithoutPlayerDto> scores = player.getScores().stream().map(score -> new ScoreWithoutPlayerDto(
                score.getId(),
                score.getScore(),
                score.getSnake(),
                score.getPlayedAt()
        )).toList();

        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getAge(),
                player.getCategory(),
                player.getCreated_at(),
                scores);
    }

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }


    public PlayerDto getPlayer(int id) {
        return playerRepo.findById(id).map(this::mapToPlayerDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This player was not found"));
    }

    public List<PlayerDto> getAllPlayers() {
        return StreamSupport.stream(playerRepo.findAll().spliterator(), false).map(this::mapToPlayerDto)
                .toList();
    }

    public PlayerDto addPlayer(PlayerParams playerParams) {
        Player player = new Player(playerParams.getName(), playerParams.getAge());
        return mapToPlayerDto(playerRepo.save(player));
    }

    public void deletePlayer(int id) {
        playerRepo.deleteById(id);
    }

    public StatsResponse getStats(int playerId) {
        Player player = playerRepo.findById(playerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player was not found"));
        return new StatsResponse(
                playerId,
                player.getScores().stream()
                .collect(Collectors.groupingBy(
                        Score::getSnake, Collectors.summarizingDouble(Score::getScore))).
                entrySet().stream()
                .map(e -> new StatsItem(e.getKey(), (int) e.getValue().getMin(), (int) e.getValue().getMax(), e.getValue().getAverage()))
                .toList());
    }
    public record StatsItem(String snake, int min, int max, double average) {}
    public record StatsResponse(int playerId, List<StatsItem> stats) {}


    public record PlayerDto(int id, String name, int age, String category, LocalDateTime createdAt, List<ScoreWithoutPlayerDto> scores) {}
    public record ScoreWithoutPlayerDto(int id, int score, String snake, LocalDateTime playedAt) {}
}
