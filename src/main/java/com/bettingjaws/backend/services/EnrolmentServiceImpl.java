package com.bettingjaws.backend.services;

import com.bettingjaws.backend.repository.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrolmentServiceImpl implements EnrolmentService{
    @Autowired
    private EnrolmentRepository enrolmentRepository;
    public boolean enrollToACourse(String email, String activityName) {
        return enrolmentRepository.enrollToACourse(email, activityName);
    }
}
