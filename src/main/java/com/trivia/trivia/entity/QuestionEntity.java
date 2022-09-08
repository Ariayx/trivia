package com.trivia.trivia.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="questions")
public class QuestionEntity {
    // website will not write question to database
    @Id
    @Column(name="id")
    public long id;

    @Column(name="type")
    public String type;

    @Column(name="title")
    public String title;

    @Column(name="answer")
    public String answer;

    @Transient
    public List<String> options;

    @Transient
    public List<String> headers;


    public QuestionEntity() {
    }

    public QuestionEntity(long id, String type, String title, String answer) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.answer = answer;
    }

    public void loadFromOptions(List<QuestionOptionEntity> options) {
        if(this.headers == null){
            this.headers = new ArrayList<>();
        }
        if (this.options == null){
            this.options = new ArrayList<>();
        }
        for (QuestionOptionEntity op : options) {
            if (op.header){
                this.headers.add(op.option);
            } else{
                this.options.add(op.option);
            }
        }
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
