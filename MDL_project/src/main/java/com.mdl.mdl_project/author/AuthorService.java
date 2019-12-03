package com.mdl.mdl_project.author;



import com.mdl.mdl_project.article.Article;
import com.mdl.mdl_project.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /*
     * Return authors
     */
    public List<Author> getAuthor(String article){

        List<Author> authors = new ArrayList<>();
        authorRepository.findByAuthor(article).forEach(authors :: add);
        return authors;
    }

    /*
     * Return one Article in terms of its ID
     */
    public Article getArticle(Integer id){


        return authorRepository.findById(id).orElse(null);
    }



}
