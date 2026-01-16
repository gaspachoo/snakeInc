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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private PlayerRepo repo;
    private final String NOM;
    private final int AGE;

    @InjectMocks
    PlayerService service;

    public ServiceTest(){
        this.NOM = "Test";
        this.AGE = 18;
    }

    @Test
    public void testAddPlayer(){
        PlayerParams playerParams = new PlayerParams(NOM, AGE);
        Player persisted = new Player(NOM, AGE);
        when(repo.save(any(Player.class))).thenReturn(persisted);

        service.addPlayer(playerParams);

        ArgumentCaptor<Player> captor = ArgumentCaptor.forClass(Player.class);
        verify(repo, times(1)).save(captor.capture());
        Player saved = captor.getValue();
        assertEquals(NOM, saved.getName());
        assertEquals(AGE, saved.getAge());
    }

    @Test
    public void testGetPlayers(){
        Player expectedPlayer = new Player(NOM,AGE);
        when(repo.findById(1)).thenReturn(Optional.of(expectedPlayer));

        PlayerService.PlayerDto result = service.getPlayer(1);

        assertEquals(NOM, result.getName());
        assertEquals(AGE, result.getAge());
        verify(repo, times(1)).findById(1);
    }

}
