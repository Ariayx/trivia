package com.trivia.trivia.repository;

import com.trivia.trivia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUserId(UUID userId);
}
