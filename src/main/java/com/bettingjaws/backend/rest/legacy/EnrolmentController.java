package com.bettingjaws.backend.rest.legacy;

import com.bettingjaws.backend.services.EnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bettingjaws/v1/")
public class EnrolmentController {
    @Autowired
    private EnrolmentService enrolmentService;

    @PostMapping("enroll")
    public boolean enrollToCourse(
            @RequestParam String email,
            @RequestParam String activityName
    ){
        return enrolmentService.enrollToACourse(email, activityName);
    }
}
