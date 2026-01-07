package org.snakeinc.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.snakeinc.api.repository.PlayerRepo;
import org.snakeinc.api.service.PlayerService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private PlayerRepo repo;

    @InjectMocks
    PlayerService service;

    @Test
    public void testAddPlayer(){
        PlayerParams playerParams = new PlayerParams("nom", 20);
        service.addPlayer(playerParams);

        ArgumentCaptor<Player> captor = ArgumentCaptor.forClass(Player.class);
        verify(repo, times(1)).save(captor.capture());
        Player saved = captor.getValue();
        assertEquals("nom", saved.getName());
        assertEquals(20, saved.getAge());
    }

    @Test
    public void testGetPlayers(){
        Player expectedPlayer = new Player("nom", 20);
        when(repo.findById(1)).thenReturn(Optional.of(expectedPlayer));

        Player result = service.getPlayer(1);

        assertEquals("nom", result.getName());
        assertEquals(20, result.getAge());
        verify(repo, times(1)).findById(1);
    }

}
