package com.mdl.mdl_project.art.history;

import com.mdl.mdl_project.article.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SOAModification implements Serializable {
    private LocalDateTime date;
    private String modification;
    private Article article;

    public SOAModification(LocalDateTime date, String modification, Article article) {
        this.date = date;
        this.modification = modification;
        this.article = article;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
