package com.mdl.mdl_project.art;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdl.mdl_project.art.tag.TagSoa;
import com.mdl.mdl_project.author.Author;
import com.mdl.mdl_project.connection.user.User;
import com.mdl.mdl_project.research.Research;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "StateOfArt")
public class StateOfArt {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String date;
    //private ArrayList<String> co_author;
    @Column(length=1000000)
    private String theabstract;
    //private ArrayList<Article> articles;
    private LocalDateTime lastModified;
    private Integer cluster;
    private String role;
    //free, approuved, close
    private String contribution;


    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(name = "coauthor_soa",
            joinColumns = @JoinColumn(name = "stateofart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors = new ArrayList<>();

    /*
    //@JsonIgnore*/
    /*
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
  */

    // Join State of art and Tag
    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(name = "indexSoaTag",
            joinColumns = @JoinColumn(name = "stateofart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tagsoa_id", referencedColumnName = "id"))
    private List<TagSoa> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public StateOfArt(){}

    public StateOfArt(Integer id, String title, String date,ArrayList<String> co_author,String theabstract, List<TagSoa> tags, User user, List<Author> authors, LocalDateTime lastModified){

        super();
        this.id = id;
        this.title = title;
        this.date =date;
        this.theabstract = theabstract;
        //this.articles = articles;
       this.tags = tags;

        this.user = user;
        this.authors= authors;
        this.lastModified = lastModified;

    }

    public void setId( Integer id){this.id = id;}

    public Integer getId (){return  this.id;}

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCluster() {
        return cluster;
    }

    public void setCluster(Integer cluster) {
        this.cluster = cluster;
    }

    public void setDate(String date ) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }


    public void setAuthors( List<Author>  co_author ) {
        this.authors = co_author;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }


    public void setTheabstract( String theabstract  )  { this.theabstract = theabstract ; }

    public String getTheabstract() {
        return theabstract;
    }
    /*
    public void setArticles( ArrayList<Article>  author ) { this.articles = articles;}
    public ArrayList<Article> getArticles() { return articles; } */


    public void setTags(List<TagSoa> tags) {
        this.tags = tags;
    }

    public  List<TagSoa> getTags() {
        return tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }




}