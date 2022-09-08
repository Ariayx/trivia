package com.trivia.trivia.repository;

import com.trivia.trivia.entity.QuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOptionEntity, Long> {
    List<QuestionOptionEntity> findOptionByQuestionId(long questionId);
    String findOptionByQuestionIdAndOption(long questionId, String option);
}
