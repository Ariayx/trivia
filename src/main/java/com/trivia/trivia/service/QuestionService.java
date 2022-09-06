package com.trivia.trivia.service;

import com.trivia.trivia.entity.QuestionEntity;

import java.util.UUID;

public interface QuestionService {
    QuestionEntity getNewQuestion(UUID userId);
}
