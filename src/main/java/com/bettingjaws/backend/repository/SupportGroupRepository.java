package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.api.dtos.Specialist;
import com.bettingjaws.backend.api.dtos.SupportGroup;
import com.bettingjaws.backend.services.SupportGroupService;

import java.util.List;

public interface SupportGroupRepository {
    public SupportGroup getSupportGroupById(String id);
    public List<SupportGroup> getAllSupportGroups(Integer limit, Integer offset, String contry);
    public Integer getSupportGroupNumber(String country);
}
