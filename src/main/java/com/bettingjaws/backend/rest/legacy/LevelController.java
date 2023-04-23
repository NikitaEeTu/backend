package com.bettingjaws.backend.rest.legacy;

import com.bettingjaws.backend.persistence.entity.GameLevel;
import com.bettingjaws.backend.repository.GameLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bettingjaws/v1/")
public class LevelController {
    @Autowired
    private GameLevelRepository gameLevelRepository;

    @GetMapping("levels")
    @ResponseBody
    public List<GameLevel> getAllLevels(){
       return gameLevelRepository.getAllLevels();
    }
    @GetMapping("level/number")
    @ResponseBody
    public GameLevel getLevelByNumber(
            @RequestParam Integer number
    ){
        return gameLevelRepository.getLevelByNumber(number);
    }
}
