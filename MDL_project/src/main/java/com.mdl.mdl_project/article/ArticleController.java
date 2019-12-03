package com.mdl.mdl_project.article;

import com.mdl.mdl_project.art.StateOfArt;
import com.mdl.mdl_project.art.StateOfArtService;
import com.mdl.mdl_project.art.history.History;
import com.mdl.mdl_project.art.history.HistoryRepository;
import com.mdl.mdl_project.art.history.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleservice;
    @Autowired
    private StateOfArtService stateOfArtService;
    @Autowired
    private HistoryRepository historyRepository;

    @RequestMapping("/StateOfArts/{id}/articles")
    public List<Article> getAllArtciles(@PathVariable Integer id){

        return articleservice.getAllArticles(id);
    }

    /*@RequestMapping("/StateOfArts/{id}/articles/tags")
    public List<Tag> getAllTagss(@PathVariable Integer id){
        return articleservice.getAllTags(id);
    }*/

    @RequestMapping("/StateOfArts/{StateOfArtsid}/articles/{id}")
    public Article getArticle(@PathVariable Integer id){

        return articleservice.getArticle(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/StateOfArts/{StateOfArtsid}/articles")
    public void addArticle( @RequestBody Article article, @PathVariable Integer StateOfArtsid){

        article.setStateOfArt (new StateOfArt(StateOfArtsid,"","",null,"",null,null,null,null));

        LocalDateTime ldt = LocalDateTime.now();

        stateOfArtService.getStateOfArt(StateOfArtsid).setLastModified(ldt);

        article = articleservice.addArticle(article);
        historyRepository.save(new History(ldt, article.getId(),"add article", StateOfArtsid));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/StateOfArts/{StateOfArtsid}/articles/{id}")
    public void updateArticle(@RequestBody Article article,@PathVariable Integer StateOfArtsid, @PathVariable Integer id ){

        article.setStateOfArt (new StateOfArt(StateOfArtsid,"","",null,"",null,null,null,null));

        LocalDateTime ldt = LocalDateTime.now();

        stateOfArtService.getStateOfArt(StateOfArtsid).setLastModified(ldt);
        historyRepository.save(new History(ldt, id, "update article",StateOfArtsid));

        articleservice.updateArticle(id,article);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/StateOfArts/{StateOfArtsid}/articles/{id}")
    public void deleteArticle(@PathVariable Integer id, @PathVariable Integer StateOfArtsid ){
        LocalDateTime ldt = LocalDateTime.now();

        stateOfArtService.getStateOfArt(StateOfArtsid).setLastModified(ldt);
        historyRepository.save(new History(ldt, id, "delete article", StateOfArtsid));

        articleservice.deleteArticle(id);
    }

}