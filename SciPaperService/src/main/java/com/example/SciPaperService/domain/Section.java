package com.example.SciPaperService.domain;

import javax.persistence.*;

@Embeddable
public class Section {

    private String name;
    private String content;

    public Section() {
    }

    public Section(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
