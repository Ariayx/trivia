package com.trivia.trivia.repository;

import com.trivia.trivia.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByUserId(UUID userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE answers SET deleted=true WHERE user_id=?1", nativeQuery = true)
    void deleteByUserId(UUID userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM answers WHERE user_id=?1 AND question_id=?2", nativeQuery = true)
    void clearAnsweredQuestion(UUID userId, long questionId);
}
