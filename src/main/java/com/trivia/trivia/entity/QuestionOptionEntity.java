package com.trivia.trivia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="question_options")
public class QuestionOptionEntity {
    @Id
    @Column(name="id")
    public long id;

    @Column(name="question_id")
    public long questionId;

    @Column(name="header")
    public boolean header;

    @Column(name="option")
    public String option;

    public QuestionOptionEntity() {
    }

    public QuestionOptionEntity(long id, long questionId, boolean header, String option) {
        this.id = id;
        this.questionId = questionId;
        this.header = header;
        this.option = option;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
