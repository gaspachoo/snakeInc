package org.snakeinc.api.service;

import lombok.Data;
import org.snakeinc.api.entity.Player;
import org.springframework.stereotype.Service;
import org.snakeinc.api.repository.PlayerRepo;
import java.util.Optional;

@Service @Data
public class PlayerService {
    private final PlayerRepo repo;

    public PlayerService(PlayerRepo repo) {
        this.repo = repo;
    }


    public Optional<Player> getPlayer(int id) {
        return repo.findById(id);
    }

    public void addPlayer(Player player) {
        repo.save(player);
    }

    public void deletePlayer(int id) {
        repo.deleteById(id);
    }
}
