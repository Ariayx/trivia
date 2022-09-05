package com.trivia.trivia.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="answers")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    private long answerId;

    @Column(name="user_id")
    private UUID userId;

    @Column(name="question_id")
    private int questionId;

    @Column(name="header")
    private String header;

    @Column(name="answer", columnDefinition = "text[]")
    private String[] answer;

    public AnswerEntity(){
    }

    public AnswerEntity(long answerId, UUID userId, int questionId, String header, String[] answer) {
        this.answerId = answerId;
        this.userId = userId;
        this.questionId = questionId;
        this.header = header;
        this.answer = answer;
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

    public int getQuestionId() {
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

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }
}
