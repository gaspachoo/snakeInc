package org.snakeinc.api.service;


import lombok.Data;
import org.snakeinc.api.entity.Player;
import org.snakeinc.api.entity.Score;
import org.snakeinc.api.entity.ScoreParams;
import org.snakeinc.api.repository.PlayerRepo;
import org.snakeinc.api.repository.ScoreRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @Data
public class ScoreService {
    private final ScoreRepo scoreRepo;
    private final PlayerRepo playerRepo;

    public Score addScore(ScoreParams scoreParams) {
        Player player = playerRepo.findById(scoreParams.getPlayerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This player was not found"));
        Score score = new Score(scoreParams.getScore(), player, scoreParams.getSnake());
        return scoreRepo.save(score);
    }

    public List<Score> getScores(ScoreParams scoreParams) {
        return scoreRepo.findBySnakeAndPlayer_Id(
                scoreParams.getSnake(),
                scoreParams.getPlayerId()
        );
    }


}
