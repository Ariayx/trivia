package com.trivia.trivia.repository;

import com.trivia.trivia.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    QuestionEntity findById(long id);

//    SELECT id
//    FROM questions
//    WHERE id NOT IN (
//            SELECT question_id
//            FROM answers
//            WHERE user_id='123e4567-e89b-12d3-a456-426614174001' AND deleted=false
//    )
//    LIMIT 1
    @Query(value="SELECT id FROM questions WHERE id NOT IN ( SELECT question_id FROM answers WHERE user_id=?1 AND deleted=false) LIMIT 1", nativeQuery = true)
    Long getUniqueQuestionByUserId(UUID userId);
}
