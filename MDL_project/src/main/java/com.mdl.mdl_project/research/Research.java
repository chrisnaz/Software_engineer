package com.mdl.mdl_project.research;

import com.mdl.mdl_project.connection.user.User;

import javax.persistence.*;

/**
 * @author Andry Rajaona
 * @version 1.1
 * This class allow to save a keyword used for research state of art
 * It is associated with a user
 */

@Entity
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String keyword;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Research(){
        super();
    }

    public Research(String date, String keyword, User user){
        this.date = date;
        this.keyword = keyword;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
