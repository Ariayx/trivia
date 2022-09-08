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
    public AnswerEntity saveAQuestionAnswer(AnswerEntity answer) {
        answerRepository.clearAnsweredQuestion(answer.userId, answer.questionId);
        return answerRepository.save(answer);
    }

    @Override
    public List<AnswerEntity> saveAQuestionAnswers(List<AnswerEntity> answers) {
        if (answers == null || answers.size() == 0){
            return null;
        }
        answerRepository.clearAnsweredQuestion(answers.get(0).userId, answers.get(0).questionId);
        return answerRepository.saveAll(answers);
    }

    @Override
    public void deleteAnswerByUserId(UUID userId) {
        answerRepository.deleteByUserId(userId);
    }

    @Override
    public void clearUserQuestionAnswers(UUID userId, long questionId) {
        answerRepository.clearAnsweredQuestion(userId, questionId);
    }
}
