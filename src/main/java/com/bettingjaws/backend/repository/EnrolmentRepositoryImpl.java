package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.persistence.entity.ResponsibleGamblingActivity;
import com.bettingjaws.backend.persistence.entity.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EnrolmentRepositoryImpl implements EnrolmentRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final ResponsibleGamblingRepository responsibleGamblingRepository;

    public EnrolmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, UserRepository userRepository, ResponsibleGamblingRepository responsibleGamblingRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.responsibleGamblingRepository = responsibleGamblingRepository;
    }

    @Override
    public boolean enrollToACourse(String email, String activityName) {
        Optional<User> fetchedUser = userRepository.getAUserByEmail(email);
        Optional<ResponsibleGamblingActivity> fetchedResponsibleGambling = responsibleGamblingRepository.getResponsibleGamblingActivity(activityName);
        String fetchedUserUuid = fetchedUser.get().getId();
        String fetchedResponsibleGamblingUuid = fetchedResponsibleGambling.get().getId();
        String generatedUuid = UUID.randomUUID().toString();
        String currentDate = Date.valueOf(LocalDate.now()).toString();
        jdbcTemplate.update("INSERT INTO enrollment_table VALUES(:uuid,:activityId, :userId, :date)", Map.of("userId", fetchedUserUuid, "uuid", generatedUuid, "activityId", fetchedResponsibleGamblingUuid, "date", currentDate));
        return true;
    }
}
