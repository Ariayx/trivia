package com.trivia.trivia.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @Column(name="userId")
    public UUID userId;

    @Column(name="answer_question_id")
    public Long answerQuestionId;

    public UserEntity() {
    }

    public UserEntity(UUID userId) {
        this.userId = userId;
        this.answerQuestionId = null;
    }

    public UserEntity(UUID userId, Long answerQuestionId) {
        this.userId = userId;
        this.answerQuestionId = answerQuestionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Long getAnswerQuestionId() {
        return answerQuestionId;
    }

    public void setAnswerQuestionId(Long answerQuestionId) {
        this.answerQuestionId = answerQuestionId;
    }
}
