package com.bettingjaws.backend.services;

import com.bettingjaws.backend.persistence.entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getAUserByEmail(String email);
    public Boolean updateUserXpLevel(String email, Integer xpLevel, Integer xpNumberBar);
    public User createAUser(User user);
   public Boolean updateUserLevel(Integer level, String email);
}
