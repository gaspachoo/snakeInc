package org.snakeinc.api.repository;

import org.snakeinc.api.entity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepo extends CrudRepository<Score, Integer> {
}
