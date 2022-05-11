package com.example.SciPaperService.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @ElementCollection
    @CollectionTable(
            name="SECTION",
            joinColumns=@JoinColumn(name="PAPER_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Section> sections;

    public Paper(String title, String author, List<Section> sections) {
        this.title = title;
        this.author = author;
        this.sections = sections;
    }

    public Paper() {
    }

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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
