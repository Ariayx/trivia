package com.trivia.trivia.service.Impl;

import com.trivia.trivia.entity.AnswerEntity;
import com.trivia.trivia.repository.AnswerRepository;
import com.trivia.trivia.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<AnswerEntity> saveAnswers(List<AnswerEntity> answers) {
        return answerRepository.saveAll(answers);
    }

    @Override
    public void deleteAnswerByUserId(UUID userId) {
        answerRepository.deleteByUserId(userId);
    }
}
