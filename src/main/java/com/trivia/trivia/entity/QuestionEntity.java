package com.trivia.trivia.entity;

import javax.persistence.*;

@Entity
@Table(name="questions")
public class QuestionEntity {
    @Id
    @Column(name="id")
    public Long id;

    @Column(name="type")
    public String type;

    @Column(name="title")
    public String title;

    @Column(name="options")
    public String[] options;

    @Column(name="headers")
    public String[] headers;

    @Column(name="answer")
    public String answer;

    public QuestionEntity() {
    }

    public QuestionEntity(Long id, String type, String title, String[] options, String[] headers, String answer) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.options = options;
        this.headers = headers;
        this.answer = answer;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
