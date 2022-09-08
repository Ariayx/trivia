package com.trivia.trivia.service;

import com.trivia.trivia.entity.UserEntity;

import java.util.UUID;

public interface UserService {
    UserEntity saveUser(UserEntity userEntity);
    UserEntity getUserById(UUID userId);
}
