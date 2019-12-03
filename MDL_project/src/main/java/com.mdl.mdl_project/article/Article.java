package com.mdl.mdl_project.article;

import com.mdl.mdl_project.art.StateOfArt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String date;
    private ArrayList<String> co_author;
    private String theabstract;
    private boolean visible;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id")
    )
    private List<Tag> tags;

    @ManyToOne
    private StateOfArt stateofart;

    public Article(){}

    public Article(Integer id, String author, String title, String date,ArrayList<String> co_author,String theabstract,List<Tag> tags,
                   Integer stateofartID){
        super();
        this.id = id;
        this.title = title;
        this.author =author;
        this.date =date;
        this.co_author = co_author;
        this.theabstract = theabstract;
        this.tags = tags;

        this.stateofart = new StateOfArt(stateofartID,"","",null,"",null,null,null,null);



    }

    public void setId( Integer id){this.id = id;}

    public Integer getId (){return  this.id;}

    public void setAuthor( String author ) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }


    public void setCoauthor( ArrayList<String>  co_author ) {
        this.co_author = co_author;
    }

    public ArrayList<String>  getCoauthor() {
        return co_author;
    }


    public void setTheabstract( String theabstract  )  { this.theabstract = theabstract ; }

    public String getTheabstract() {
        return theabstract;
    }


    public void setTags( List<Tag>  tags ) {
        this.tags = tags;
    }

    public  List<Tag> getTags() {
        return tags;
    }

    public StateOfArt getStateOfArt(){
        return this.stateofart;
    }

    public void setStateOfArt(StateOfArt stateoftheart){
        this.stateofart = stateoftheart;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}