package com.mdl.mdl_project.art.history;

import com.mdl.mdl_project.art.StateOfArt;
import com.mdl.mdl_project.article.Article;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class History{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDateTime dateTime;
    private Integer articleId;
    private String modification;
    private Integer idSOA;




    public History(LocalDateTime dateTime, Integer articleId, String modification, Integer idSOA) {
        this.dateTime = dateTime;
        this.articleId = articleId;
        this.modification = modification;
        this.idSOA = idSOA;
    }
    public History(){}

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getIdSOA() {
        return idSOA;
    }

    public void setIdSOA(Integer idSOA) {
        this.idSOA = idSOA;
    }
}
