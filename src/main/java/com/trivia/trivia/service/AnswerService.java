package com.trivia.trivia.service;

import com.trivia.trivia.entity.AnswerEntity;

import java.util.UUID;

public interface AnswerService {
    AnswerEntity saveAnswer(AnswerEntity answer);
    void deleteAnswerByUserId(UUID uuid);
}
