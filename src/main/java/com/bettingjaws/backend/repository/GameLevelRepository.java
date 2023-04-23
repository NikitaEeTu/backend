package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.persistence.entity.GameLevel;

import java.util.List;

public interface GameLevelRepository {
    public List<GameLevel> getAllLevels();

    public GameLevel getLevelByNumber(Integer levelNumber);
}
