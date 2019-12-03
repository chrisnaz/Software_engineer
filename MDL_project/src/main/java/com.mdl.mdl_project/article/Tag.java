package com.mdl.mdl_project.article;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Tag")
@Table(name = "tag_for_article")
public class Tag {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;


    @Column(length = 50)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Article> articles = new ArrayList<>();

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    public String getTag() {
        return this.name;
    }
}
