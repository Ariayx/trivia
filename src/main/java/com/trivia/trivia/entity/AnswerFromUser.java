package com.trivia.trivia.entity;

import java.util.Map;

public class AnswerFromUser {
    public long questionId;
    public Map<String, String[]> answers;

    public AnswerFromUser() {
    }

    public AnswerFromUser(long questionId, Map<String, String[]> answers) {
        this.questionId = questionId;
        this.answers = answers;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public Map<String, String[]> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String[]> answers) {
        this.answers = answers;
    }
}
