package org.snakeinc.api;

import org.junit.jupiter.api.Test;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.PlayerParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void testPost(){
        PlayerParams params = new PlayerParams("nom", 21);
        Player player = restTemplate.postForEntity("/api/v1/players", params, Player.class).getBody();
        assert(player.getName().equals("nom"));
        assert(player.getAge() == 21);
    }

    @Test
    public void testGet(){
        PlayerParams params = new PlayerParams("nom", 21);
        Player playerPost = restTemplate.postForEntity("/api/v1/players", params, Player.class).getBody();
        Player playerGet = restTemplate.getForEntity("/api/v1/players/" + playerPost.getId(), Player.class).getBody();
        assert(playerPost.getName().equals(playerGet.getName()));
        assert(playerPost.getAge() == playerGet.getAge());
    }

}
