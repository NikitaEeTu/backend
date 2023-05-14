package com.bettingjaws.backend.repository;

public interface EnrolmentRepository {
    boolean enrollToACourse(String email, String activityName);
}
