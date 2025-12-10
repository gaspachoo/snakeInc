package org.snakeinc.api.controller;


import jakarta.validation.Valid;
import org.snakeinc.api.entities.Player;
import org.snakeinc.api.entities.PlayerParams;
import org.snakeinc.api.service.PlayersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/players")
public class PlayersController {

    private final PlayersService service;

    public PlayersController(PlayersService service) {
        this.service = service;
    }


    @GetMapping("{id}")
    public Player getAPlayer(@PathVariable int id) {
        return service.getPlayer(id);
    }

    @PostMapping
    public Player postPlayer(@RequestBody @Valid PlayerParams params){
        Player player = new Player(params.getName(), params.getAge());
        service.addPlayer(player);
        return player;
    }
}
