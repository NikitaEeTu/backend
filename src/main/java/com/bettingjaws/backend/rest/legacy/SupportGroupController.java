package com.bettingjaws.backend.rest.legacy;

import com.bettingjaws.backend.api.dtos.Specialist;
import com.bettingjaws.backend.api.dtos.SupportGroup;
import com.bettingjaws.backend.repository.SupportGroupRepository;
import com.bettingjaws.backend.services.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bettingjaws/v1")
public class SupportGroupController {
    @Autowired
    private SupportGroupRepository supportGroupRepository;

    @GetMapping(value = "supportgroup/{id}")
    @ResponseBody
    public String getSpecialistById(@PathVariable("id") String id){
        return supportGroupRepository.getSupportGroupById(id).groupName();
    }

    @GetMapping(value = "/supportgroups")
    @ResponseBody
    public List<SupportGroup> getAllSpecialist(
            @RequestParam Integer limit,
            @RequestParam Integer offset,
            @RequestParam String country
    ){
        return  supportGroupRepository.getAllSupportGroups(
                limit, offset, country
        );
    }

    @GetMapping(value = "/number/supportgroups")
    @ResponseBody
    public Integer getAllSpecialistNumber(
            @RequestParam String country
    ){
        return supportGroupRepository.getSupportGroupNumber(country);
    }
}
