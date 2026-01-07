package org.snakeinc.api.controller;

import jakarta.validation.Valid;
import org.snakeinc.api.entity.Score;
import org.snakeinc.api.entity.ScoreParams;
import org.snakeinc.api.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/scores")
public class ScoresController {
    private final ScoreService scoreService;

    public ScoresController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public Score addScore(@RequestBody @Valid ScoreParams scoreParams) {
        return scoreService.addScore(scoreParams);
    }

    @GetMapping
    public List<Score> getScores(
            @RequestParam(name = "snake") String snake,
            @RequestParam(name = "player") int playerId
    ) {
        ScoreParams params = new ScoreParams(playerId, snake);
        return scoreService.getScores(params);
    }
}
