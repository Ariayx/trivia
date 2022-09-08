package com.trivia.trivia.service;

import com.trivia.trivia.entity.AnswerEntity;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    AnswerEntity saveAQuestionAnswer(AnswerEntity answer);
    List<AnswerEntity> saveAQuestionAnswers(List<AnswerEntity> answers);
    void deleteAnswerByUserId(UUID userId);
    void clearUserQuestionAnswers(UUID userId, long questionId);
}
