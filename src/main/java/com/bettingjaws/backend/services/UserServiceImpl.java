package com.bettingjaws.backend.services;

import com.bettingjaws.backend.persistence.entity.User;
import com.bettingjaws.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getAUserByEmail(String email) {
        return userRepository.getAUserByEmail(email);
    }

    @Override
    public Boolean updateUserXpLevel(String email, Integer xpLevel, Integer xpNumberBar) {
        return userRepository.updateUserXpNumber(email, xpLevel, xpNumberBar);
    }

    @Override
    public User createAUser(User user) {
        return userRepository.createAUser(user);
    }

    @Override
    public Boolean updateUserLevel(Integer level, String email) {
        return userRepository.updateUserLevel(level, email);
    }
}
