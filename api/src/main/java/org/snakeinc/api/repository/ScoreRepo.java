package org.snakeinc.api.repository;

import org.snakeinc.api.entity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepo extends CrudRepository<Score, Integer> {
    List<Score> findBySnakeAndPlayer_Id(String snake, int playerId);
}
