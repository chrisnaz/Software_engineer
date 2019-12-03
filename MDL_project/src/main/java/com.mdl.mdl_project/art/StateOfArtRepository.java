package com.mdl.mdl_project.art;

import com.mdl.mdl_project.article.Article;
import com.mdl.mdl_project.connection.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateOfArtRepository extends CrudRepository<StateOfArt, Integer> {

    public List<StateOfArt> findByUserId(Long stateofartId);




}