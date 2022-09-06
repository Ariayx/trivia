package com.trivia.trivia.repository;

import com.trivia.trivia.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByUserId(UUID userId);
//    AnswerEntity findFirstByUserIdAndQuestionId(UUID userId, int questionId);
    long deleteByUserId(UUID userId);
}
