package com.bettingjaws.backend.repository;

import com.bettingjaws.backend.persistence.entity.User;

import java.util.Optional;

public interface UserRepository {
    public Optional<User> getAUserByEmail(String email);
    public User createAUser(User user);
    Optional<User> loadUserByUsername(String username);
    public Boolean updateUserLevel(Integer level, String email);
    public Boolean updateUserXpNumber(String email, Integer xpNumber, Integer xpNumberBar);
}
