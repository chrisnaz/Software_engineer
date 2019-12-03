package com.mdl.mdl_project.article;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articlerepository;

    /*
     * Return All Articles
     */
    public List<Article> getAllArticles(Integer stateofartId){

        List<Article> articles = new ArrayList<>();
        articlerepository.findByStateofartId(stateofartId).forEach(articles :: add); // lambda expression

        return articles;
    }

    /*public List<Tag> getAllTags(Integer stateofartId){

        List<Tag> tags_articles  = new ArrayList<>();
        articlerepository.findByStateofartId(stateofartId).forEach(tags_articles ::add); // lambda expression

        return tags_articles;
    }*/

    /*
     * Return one Article in terms of its ID
     */
    public Article getArticle(Integer id){


        return articlerepository.findById(id).orElse(null);
    }

    /*
     * Add oneArticles
     */
    public Article addArticle(Article article){
        //stateofarts.add(stateofart);
        return articlerepository.save(article);
    }

    /*
     * Update one Article in terms of its ID
     */
    public void updateArticle(Integer id, Article article) {

        List<Article> articles = new ArrayList<>();
        articlerepository.findAll().forEach(articles :: add);

        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId() == id) {
                articles.get(i).setVisible(article.isVisible());
                articlerepository.save(articles.get(i));
            }
        }

        //articlerepository.save(article);
    }

    /*
     * Remove one Article in terms of its ID
     */
    public void deleteArticle(Integer id){
        //stateofarts.removeIf(t -> t.getId() == id);
        articlerepository.deleteById(id);

    }


}
