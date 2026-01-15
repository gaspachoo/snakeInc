package org.snakeinc.api.controller;


import jakarta.validation.Valid;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.snakeinc.api.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/players")
public class PlayersController {

    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("{id}")
    public Player getAPlayer(@PathVariable int id) {
        return playerService.getPlayer(id);
    }

    @PostMapping
    public Player postPlayer(@RequestBody @Valid PlayerParams playerParams){
        return playerService.addPlayer(playerParams);
    }
    @DeleteMapping("{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
    }

    @GetMapping("{id}/stats")
    public PlayerService.StatsResponse getStats(@PathVariable int id) {
        return playerService.getStats(id);
    }
}
