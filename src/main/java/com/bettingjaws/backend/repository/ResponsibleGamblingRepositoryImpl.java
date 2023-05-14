package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.api.dtos.Specialist;
import com.bettingjaws.backend.api.dtos.SpecialistType;
import com.bettingjaws.backend.persistence.entity.ResponsibleGamblingActivity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class ResponsibleGamblingRepositoryImpl implements ResponsibleGamblingRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<ResponsibleGamblingActivity> responsibleGamblingActivityRowMapper = createResponsibleGamblingActivityRowMapper();

    public ResponsibleGamblingRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ResponsibleGamblingActivity> getResponsibleGamblingActivity(String name) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM responsible_gaming_activity_table WHERE activity_name=:activityName", Map.of("activityName", name), responsibleGamblingActivityRowMapper));
    }

    private RowMapper<ResponsibleGamblingActivity> createResponsibleGamblingActivityRowMapper(){
        return ((rs, rowNum) -> {
            String id = rs.getString("id");
            String activity_name = rs.getString("activity_name");
            String activity_description = rs.getString("activity_description");
            String creator_id = rs.getString("creator_id");
            Integer total_xp_number = rs.getInt("total_xp_number");
            String access_level_id = rs.getString("access_level_id");

            return new ResponsibleGamblingActivity(
                    id,
                    activity_name,
                    activity_description,
                    creator_id,
                    total_xp_number,
                    access_level_id
            );
        });
    }
}
