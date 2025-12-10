package org.snakeinc.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.snakeinc.api.entities.Player;
import org.snakeinc.api.service.PlayerService;

public class ServiceTest {
    @InjectMocks
    PlayerService service;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPlayer(){
        Player player = new Player("nom", 20);
        service.addPlayer(player);
        assert(service.getPlayers().get(player.getId()) ==  player);
    }

    @Test
    public void testGetPlayers(){
        Player player = new Player("nom", 20);
        service.addPlayer(player);
        assert(service.getPlayer(player.getId()) == player);
    }

}
