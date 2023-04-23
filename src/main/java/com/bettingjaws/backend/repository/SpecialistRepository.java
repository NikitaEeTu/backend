package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.api.dtos.Specialist;

import java.util.List;

public interface SpecialistRepository {
    public Specialist getSpecialistById(String id);
    public List<Specialist> getAllMedicalSpecialist(Integer limit, Integer offset, String contry);
    public int getAllMedicalSpecialist();
}
