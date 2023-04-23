package com.bettingjaws.backend.services;

import java.util.List;

public interface SupportGroupService {
    public SupportGroupService getSupportGroupById(String id);
    public List<SupportGroupService> getAllSupportGroups(Integer limit, Integer offset, String contry);
    public Integer getSupportGroupNumber();
}
