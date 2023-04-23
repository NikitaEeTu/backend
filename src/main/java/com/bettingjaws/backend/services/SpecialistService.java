package com.bettingjaws.backend.services;

import com.bettingjaws.backend.api.dtos.Specialist;

import java.util.List;

public interface SpecialistService {
    public Specialist getSpecialistById(String id);
    public List<Specialist> getAllSpecialist(Integer limit, Integer offset, String contry);
    public Integer getSpecialistNumber();
}
