package com.trivia.trivia.service.Impl;

import com.trivia.trivia.entity.UserEntity;
import com.trivia.trivia.repository.UserRepository;
import com.trivia.trivia.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(UUID userId) {
        return userRepository.findByUserId(userId);
    }
}
