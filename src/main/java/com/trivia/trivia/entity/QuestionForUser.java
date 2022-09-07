package com.trivia.trivia.entity;

public class QuestionForUser {
    public Long id;
    public String type;
    public String title;
    public String[] options;
    public String[] headers;

    public QuestionForUser() {
    }

    public QuestionForUser(QuestionEntity questionEntity) {
        this.id = questionEntity.id;
        this.type = questionEntity.type;
        this.title = questionEntity.title;
        this.options = questionEntity.options.clone();
        this.headers = questionEntity.headers.clone();
    }

    public Long getId() {
        return id;
    }

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
}
