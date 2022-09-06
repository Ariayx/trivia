package com.trivia.trivia.repository;

import com.trivia.trivia.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    QuestionEntity findById(long id);

    // SELECT q.id
    // FROM questions q
    // LEFT JOIN answers a ON q.id = a.question_id
    // WHERE a.question_id IS NULL
    // LIMIT 1
    @Query(value = "SELECT q.id FROM questions q LEFT JOIN answers a ON q.id = a.question_id WHERE a.question_id IS NULL LIMIT 1;", nativeQuery = true)
    QuestionEntity getUniqueQuestionByUserId(UUID userId);
}
