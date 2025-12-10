package org.snakeinc.api.controller;


import jakarta.validation.Valid;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.snakeinc.api.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/players")
public class PlayersController {

    private final PlayerService service;

    public PlayersController(PlayerService service) {
        this.service = service;
    }


    @GetMapping("{id}")
    public Optional<Player> getAPlayer(@PathVariable int id) {
        return service.getPlayer(id);
    }

    @PostMapping
    public Player postPlayer(@RequestBody @Valid PlayerParams params){
        Player player = new Player(params.getName(), params.getAge());
        service.addPlayer(player);
        return player;
    }
    @DeleteMapping("{id}")
    public void deletePlayer(@PathVariable int id) {
        service.deletePlayer(id);
    }
}
