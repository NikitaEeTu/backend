package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.api.dtos.Specialist;
import com.bettingjaws.backend.api.dtos.SpecialistType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SpecialistRepositoryImpl implements SpecialistRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Specialist> specialistRowMapper = createSpecialistRowMapper();
    private final static Integer MAX_LIMIT = 3;

    public SpecialistRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Specialist getSpecialistById(String id) {
        List<Specialist> specialistList = jdbcTemplate.query("SELECT * FROM specialist_table WHERE id = :specialistId", Map.of("specialistId", id) ,specialistRowMapper);
        return specialistList.get(0);
    }

    @Override
    public List<Specialist> getAllMedicalSpecialist(Integer limit, Integer offset, String contry) {
        if(limit > MAX_LIMIT){
            limit = MAX_LIMIT;
        }
        List<Specialist> specialistList = jdbcTemplate.query("SELECT * FROM specialist_table WHERE country = :country LIMIT :limit OFFSET :offset",
                Map.of(
                        "limit", limit,
                        "offset", offset,
                        "country", contry
                ),
                specialistRowMapper);
        return specialistList;
    }

    @Override
    public int getAllMedicalSpecialist() {
        int numberOfSpecialists = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM specialist_table WHERE type = :type", Map.of("type", "DOCTOR"), Integer.class);
        return numberOfSpecialists;
    }

    private RowMapper<Specialist> createSpecialistRowMapper(){
        return ((rs, rowNum) -> {
            String id = rs.getString("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phone_number");
            String clinicName = rs.getString("clinic_name");
            String country = rs.getString("country");
            String type = rs.getString("type");
            String website = rs.getString("website");

            return new Specialist(
                    id,
                    firstName,
                    lastName,
                    email,
                    phoneNumber,
                    clinicName,
                    country,
                    SpecialistType.valueOf(type),
                    website
                    );
        });
    }
}
