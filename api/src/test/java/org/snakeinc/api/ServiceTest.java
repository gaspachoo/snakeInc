package org.snakeinc.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.repository.PlayerRepo;
import org.snakeinc.api.service.PlayerService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @Mock
    private PlayerRepo repo;

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
        verify(repo, times(1)).save(player);
    }

    @Test
    public void testGetPlayers(){
        Player expectedPlayer = new Player("nom", 20);
        when(repo.findById(1)).thenReturn(Optional.of(expectedPlayer));
        Optional<Player> result = service.getPlayer(1);
        assertTrue(result.isPresent());
        verify(repo, times(1)).findById(1);
    }

}
