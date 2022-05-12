package com.example.Library.domain;

import javax.persistence.*;

@Entity
public class Paper {

    @Id
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Paper() {
    }

    public Paper(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
