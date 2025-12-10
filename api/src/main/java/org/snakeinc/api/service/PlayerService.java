package org.snakeinc.api.service;

import lombok.Data;
import org.snakeinc.api.entity.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service @Data
public class PlayerService {
    private final Map<Integer, Player> players = new HashMap<>();

    public Player getPlayer(int id) {
        return players.get(id);
    }

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }
}
