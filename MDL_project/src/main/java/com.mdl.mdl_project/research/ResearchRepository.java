package com.mdl.mdl_project.research;

import com.mdl.mdl_project.art.StateOfArt;
import com.mdl.mdl_project.connection.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResearchRepository extends CrudRepository <Research, Integer> {

    public Research findByKeywordAndUser(String keyword, User user);
    public List<Research> findByUserId(Long researchid);

}

