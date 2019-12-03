package com.mdl.mdl_project.author;

import com.mdl.mdl_project.art.StateOfArt;
import com.mdl.mdl_project.article.Article;
import com.mdl.mdl_project.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/Author/{author}/author")
    public List<Author> getAuthors(@PathVariable String author){

        return authorService.getAuthor(author);
    }

}
