package org.snakeinc.api.controller;


import org.snakeinc.api.entities.Player;
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
    public Player postPlayer(@RequestBody PlayerParams params){
        Player player = new Player(params.name, params.age);
        service.addPlayer(player);
        return player;
    }

    private record PlayerParams(String name, int age){}
}
