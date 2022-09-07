package com.trivia.trivia.service;

import com.trivia.trivia.entity.AnswerEntity;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    AnswerEntity saveAnswer(AnswerEntity answer);
    List<AnswerEntity> saveAnswers(List<AnswerEntity> answers);
    void deleteAnswerByUserId(UUID uuid);
}
