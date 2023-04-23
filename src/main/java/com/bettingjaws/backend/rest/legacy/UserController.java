package com.bettingjaws.backend.rest.legacy;

import com.bettingjaws.backend.persistence.entity.GameLevel;
import com.bettingjaws.backend.persistence.entity.Role;
import com.bettingjaws.backend.persistence.entity.User;
import com.bettingjaws.backend.repository.GameLevelRepository;
import com.bettingjaws.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final static String BASE_URL = "/api/bettingjaws/user";

    @Autowired
    private UserService userService;

    @Autowired
    private GameLevelRepository gameLevelRepository;

    @GetMapping(value = BASE_URL)
    public Optional<User> getAUserByEmail(
            @RequestParam("email") String email
    ){
       return userService.getAUserByEmail(email);
    }

    @PatchMapping(value = BASE_URL + "/update/level")
    public Boolean updateUserLevel(
            @RequestParam("level") Integer level,
            @RequestParam("email") String email
    ){
        return userService.updateUserLevel(level, email);
    }

    @PatchMapping(value = BASE_URL + "/update/xp-points")
    public Boolean updateXpPoints(
            @RequestParam("email") String email,
            @RequestParam("xp") int xpNumber,
            @RequestParam("xpBar") int xpBar,
            @RequestParam("level") int level

    ){
        final List<GameLevel> gameLevels = gameLevelRepository.getAllLevels();
        GameLevel gameLevel = filterGameLevels(gameLevels, xpNumber, level);
        if(level != gameLevel.getLevel_number()){
            this.userService.updateUserLevel(gameLevel.getLevel_number(), email);
            xpBar = 0;
        }
        return userService.updateUserXpLevel(email, xpNumber, xpBar);
    }

    private GameLevel filterGameLevels(List<GameLevel> gameLevels, Integer curUserXp, Integer curUserLevel){
       List<GameLevel> gameLevelList = gameLevels.stream().filter(gameLevel -> curUserXp >= gameLevel.getXp_required() && curUserLevel < gameLevel.getLevel_number()
        ).toList();
       if(gameLevelList.size() == 0){
           return gameLevels.stream().filter(gameLevel -> curUserLevel == gameLevel.getLevel_number()
           ).toList().get(0);
       }
       else{
           return gameLevelList.get(0);
       }
    }

        @PostMapping(value = BASE_URL)
    @ResponseBody
    public User createAUser (
            @RequestBody User user
    ) throws Exception {
            Optional<User> foundUsed = userService.getAUserByEmail(user.getEmail());
            if(foundUsed.isEmpty()) {
                User newUser = new User(
                        UUID.randomUUID().toString(),
                        user.getFirst_name(),
                        user.getLast_name(),
                        user.getEmail(),
                        user.getPhone_number(),
                        user.getCountry(),
                        0,
                        0,
                        0,
                        Role.USER,
                        user.getPassword()
                );
                return userService.createAUser(newUser);
            }
            else{
                throw new Exception("This user is already created");
            }

    }
}
