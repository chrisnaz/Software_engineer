package com.mdl.mdl_project.author;

import com.mdl.mdl_project.article.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Article, Integer> {

    public List<Author> findByAuthor(String author);

}