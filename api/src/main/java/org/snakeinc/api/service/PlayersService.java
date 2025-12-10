package org.snakeinc.api.service;

import org.snakeinc.api.entities.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayersService {
    private final Map<Integer, Player> players = new HashMap<>();

    public Player getPlayer(int id) {
        return players.get(id);
    }

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }
}
