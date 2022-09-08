package com.trivia.trivia.service;

import com.trivia.trivia.entity.QuestionEntity;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    QuestionEntity getNewQuestion(UUID userId);
    QuestionEntity getQuestionById(long questionId);
    boolean isValidAnswer(long questionId, String answer);
}
