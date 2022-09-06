package com.trivia.trivia.service.Impl;

import com.trivia.trivia.entity.AnswerEntity;
import com.trivia.trivia.repository.AnswerRepository;
import com.trivia.trivia.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public AnswerEntity saveAnswer(AnswerEntity answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswerByUserId(UUID userId) {
        answerRepository.deleteByUserId(userId);
    }
}
