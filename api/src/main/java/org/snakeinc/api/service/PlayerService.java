package org.snakeinc.api.service;

import lombok.Data;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.snakeinc.api.entity.Score;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.snakeinc.api.repository.PlayerRepo;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service @Data
public class PlayerService {
    private final PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }


    public Player getPlayer(int id) {
        return playerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This player was not found"));
    }

    public List<Player> getAllPlayers() {
        return StreamSupport.stream(playerRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Player addPlayer(PlayerParams playerParams) {
        Player player = new Player(playerParams.getName(), playerParams.getAge());
        return playerRepo.save(player);
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
    public record StatsResponse(int playerId, List<PlayerService.StatsItem> stats) {}
}
