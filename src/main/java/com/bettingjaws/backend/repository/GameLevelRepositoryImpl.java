package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.api.dtos.Specialist;
import com.bettingjaws.backend.api.dtos.SpecialistType;
import com.bettingjaws.backend.api.dtos.SupportGroup;
import com.bettingjaws.backend.persistence.entity.GameLevel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GameLevelRepositoryImpl implements GameLevelRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<GameLevel> gameLevelRowMapper = creategameLevelRowMapper();

    public GameLevelRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GameLevel> getAllLevels() {
        return jdbcTemplate.query("SELECT * FROM game_level_table", Collections.emptyMap() ,gameLevelRowMapper);
    }

    @Override
    public GameLevel getLevelByNumber(Integer levelNumber) {
        return jdbcTemplate.queryForObject("SELECT * FROM game_level_table WHERE level_number = :level", Map.of("level", levelNumber), gameLevelRowMapper);
    }

    private RowMapper<GameLevel> creategameLevelRowMapper(){
        return ((rs, rowNum) -> {
            String id = rs.getString("id");
            Integer levelNumber = rs.getInt("level_number");
            Integer xpRequired = rs.getInt("xp_required");

            return new GameLevel(
                    id,
                    levelNumber,
                    xpRequired
            );
        });
    }
}
