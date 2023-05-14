package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.persistence.entity.ResponsibleGamblingActivity;

import java.util.Optional;

public interface ResponsibleGamblingRepository {
    public Optional<ResponsibleGamblingActivity> getResponsibleGamblingActivity(String name);
}
