package com.trivia.trivia.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="answers")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    public long answerId;

    @Column(name="user_id")
    public UUID userId;

    @Column(name="question_id")
    public long questionId;

    @Column(name="header")
    public String header;

    @Column(name="answer")
    public String answer;

    @Column(name="deleted")
    public boolean deleted;

    public AnswerEntity(){
    }

    public AnswerEntity(UUID userId, long questionId, String header, String answer, boolean deleted) {
        this.userId = userId;
        this.questionId = questionId;
        this.header = header;
        this.answer = answer;
        this.deleted = deleted;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
