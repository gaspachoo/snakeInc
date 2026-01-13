package org.snakeinc.api.service;

import lombok.Data;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.snakeinc.api.repository.PlayerRepo;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service @Data
public class PlayerService {
    private final PlayerRepo repo;

    public PlayerService(PlayerRepo repo) {
        this.repo = repo;
    }


    public Player getPlayer(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This player was not found"));
    }

    public List<Player> getAllPlayers() {
        return StreamSupport.stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Player addPlayer(PlayerParams playerParams) {
        Player player = new Player(playerParams.getName(), playerParams.getAge());
        return repo.save(player);
    }

    public void deletePlayer(int id) {
        repo.deleteById(id);
    }
}
