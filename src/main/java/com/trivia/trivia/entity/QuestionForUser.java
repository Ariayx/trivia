package com.trivia.trivia.entity;

import java.util.ArrayList;
import java.util.List;

public class QuestionForUser {
    public long id;
    public String type;
    public String title;
    public List<String> options;
    public List<String> headers;

    public QuestionForUser() {
    }

    public QuestionForUser(QuestionEntity questionEntity) {
        this.id = questionEntity.id;
        this.type = questionEntity.type;
        this.title = questionEntity.title;
        this.options = new ArrayList<>();
        for(String s : questionEntity.options) {
            this.options.add(s);
        }
        this.headers = new ArrayList<>();
        for(String s : questionEntity.headers) {
            this.headers.add(s);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
