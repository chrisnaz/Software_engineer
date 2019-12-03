package com.mdl.mdl_project.art.tag;

import org.springframework.data.repository.CrudRepository;

public interface TagSoaRepository extends CrudRepository<TagSoa, Integer> {

    public Iterable<TagSoa> findAllByName(String name);

}
