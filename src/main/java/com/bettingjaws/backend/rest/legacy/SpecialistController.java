package com.bettingjaws.backend.rest.legacy;

import com.bettingjaws.backend.api.dtos.Specialist;
import com.bettingjaws.backend.services.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bettingjaws")
public class SpecialistController {
    @Autowired
    private SpecialistService specialistService;

    @GetMapping(value = "/specialist/{id}")
    @ResponseBody
    public String getSpecialistById(@PathVariable("id") String id){
        return specialistService.getSpecialistById(id).email();
    }

    @GetMapping(value = "/specialist")
    @ResponseBody
    public List<Specialist> getAllSpecialist(
            @RequestParam Integer limit,
            @RequestParam Integer offset,
            @RequestParam String country
    ){
        return  specialistService.getAllSpecialist(
                limit, offset, country
        );
    }

    @GetMapping(value = "/number/specialist")
    @ResponseBody
    public Integer getAllSpecialistNumber(
    ){
        return specialistService.getSpecialistNumber();
    }
}
