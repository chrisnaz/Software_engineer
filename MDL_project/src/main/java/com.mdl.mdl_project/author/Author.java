package com.mdl.mdl_project.author;


import com.mdl.mdl_project.art.StateOfArt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 4)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;


    @ManyToMany(mappedBy = "authors")
    private List<StateOfArt> stateofart=new ArrayList<>();;

    public Author() {
    }

    public Author(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;

        //this.stateofart = new StateOfArt(SOAID, "", "",null,"", null,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}