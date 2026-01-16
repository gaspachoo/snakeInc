package org.snakeinc.snake.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.snakeinc.snake.dto.PlayerDTO;
import org.snakeinc.snake.dto.ScoreDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiService {
    private static final String BASE_URL = "http://localhost:8080/api/v1";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<PlayerDTO> getAllPlayers() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/players"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), new TypeReference<>() {
                });
            } else {
                System.err.println("Error fetching players: " + response.statusCode());
                return new ArrayList<>();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error connecting to API: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public PlayerDTO createPlayer(String name, int age) {
        try {
            Map<String, Object> playerPayload = new HashMap<>();
            playerPayload.put("name", name);
            playerPayload.put("age", age);

            String jsonBody = objectMapper.writeValueAsString(playerPayload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/players"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                return objectMapper.readValue(response.body(), PlayerDTO.class);
            } else {
                System.err.println("Error sending player: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error sending player: " + e.getMessage());
            return null;
        }
    }

    public void sendScore(int score, int playerId, String snakeName) {
        try {
            Map<String, Object> scorePayload = new HashMap<>();
            scorePayload.put("score", score);
            scorePayload.put("playerId", playerId);
            scorePayload.put("snake", snakeName);

            String jsonBody = objectMapper.writeValueAsString(scorePayload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/scores"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                System.out.println("Score sent successfully: " + score);
            } else {
                System.err.println("Error sending score: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error sending score: " + e.getMessage());
        }
    }

    public ScoreDTO getBestScore(String snakeName){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/scores/best?snake=" + java.net.URLEncoder.encode(snakeName, java.nio.charset.StandardCharsets.UTF_8)))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), ScoreDTO.class);
            } else {
                System.err.println("Error fetching best score: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error connecting to API: " + e.getMessage());
            return null;
        }
    }
}
