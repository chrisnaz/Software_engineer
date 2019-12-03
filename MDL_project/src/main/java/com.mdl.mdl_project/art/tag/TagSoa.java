package com.mdl.mdl_project.art.tag;

import com.mdl.mdl_project.art.StateOfArt;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andry Rajaona
 * @version 1.1
 * This class allow to manage Tag for state of art
 */
@Entity
public class TagSoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float value;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<StateOfArt> stateOfArts = new ArrayList<>();

    public TagSoa(){}

    public TagSoa(String name, Float value){
        super();
        this.name=name;
        this.value=value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public List<StateOfArt> getStateOfArts() {
        return stateOfArts;
    }

    public void setStateOfArts(List<StateOfArt> stateOfArts) {
        this.stateOfArts = stateOfArts;
    }

}
