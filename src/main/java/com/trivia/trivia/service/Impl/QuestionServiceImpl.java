package com.trivia.trivia.service.Impl;

import com.trivia.trivia.entity.QuestionEntity;
import com.trivia.trivia.repository.AnswerRepository;
import com.trivia.trivia.repository.QuestionRepository;
import com.trivia.trivia.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionEntity getNewQuestion(UUID userId) {
        Long questionId = questionRepository.getUniqueQuestionByUserId(userId);
        if(questionId == null){
            answerRepository.deleteByUserId(userId);
            questionId = questionRepository.getUniqueQuestionByUserId(userId);
        }
        return questionRepository.findById(questionId.longValue());
    }

    @Override
    public QuestionEntity getQuestionById(long questionId) {
        return questionRepository.findById(questionId);
    }
}
