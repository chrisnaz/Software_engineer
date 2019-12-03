package com.mdl.mdl_project.article;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

    public List<Article> findByTitle(String title);
    public List<Article> findByStateofartId(Integer stateofartId);
    //public List<Tag> findByfindByStateofartId(Integer stateofartId);

}