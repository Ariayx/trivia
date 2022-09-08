package com.trivia.trivia.service.Impl;

import com.trivia.trivia.entity.QuestionEntity;
import com.trivia.trivia.repository.AnswerRepository;
import com.trivia.trivia.repository.QuestionOptionRepository;
import com.trivia.trivia.repository.QuestionRepository;
import com.trivia.trivia.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionOptionRepository optionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, QuestionOptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public QuestionEntity getNewQuestion(UUID userId) {
        Long questionId = questionRepository.getUniqueQuestionByUserId(userId);
        if(questionId == null){
            answerRepository.deleteByUserId(userId);
            questionId = questionRepository.getUniqueQuestionByUserId(userId);
        }
        QuestionEntity question = questionRepository.findById(questionId).orElse(null);
        if (question != null){
            question.loadFromOptions(optionRepository.findOptionByQuestionId(questionId));
        }
        return question;
    }

    @Override
    public QuestionEntity getQuestionById(long questionId) {
        QuestionEntity question = questionRepository.findById(questionId);
        if (question != null){
            question.loadFromOptions(optionRepository.findOptionByQuestionId(questionId));
        }
        return question;
    }

    @Override
    public boolean isValidAnswer(long questionId, String answer) {
        String result = optionRepository.findOptionByQuestionIdAndOption(questionId, answer);
        return result != null;
    }


}
