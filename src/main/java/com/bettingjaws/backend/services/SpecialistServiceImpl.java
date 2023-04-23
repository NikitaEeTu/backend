package com.bettingjaws.backend.services;

import com.bettingjaws.backend.api.dtos.Specialist;
import com.bettingjaws.backend.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistServiceImpl implements SpecialistService{
    @Autowired
    private SpecialistRepository specialistRepository;
    @Override
    public Specialist getSpecialistById(String id) {
        return specialistRepository.getSpecialistById(id);
    }

    @Override
    public List<Specialist> getAllSpecialist(Integer limit, Integer offset, String contry) {
        return specialistRepository.getAllMedicalSpecialist(limit, offset, contry);
    }

    @Override
    public Integer getSpecialistNumber() {
        return specialistRepository.getAllMedicalSpecialist();
    }
}
