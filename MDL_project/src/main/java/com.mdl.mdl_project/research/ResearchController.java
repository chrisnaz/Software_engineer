package com.mdl.mdl_project.research;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdl.mdl_project.art.StateOfArt;
import com.mdl.mdl_project.art.tag.TagSoa;
import com.mdl.mdl_project.art.tag.TagSoaService;
import com.mdl.mdl_project.research.category.ResultatResearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class ResearchController {

    @Autowired
    private TagSoaService tagSoaService;
    @Autowired
    private ResearchService researchService;

    /**
     * The request that allows the search by keyword
     * @param keyword, enter by user
     * @return A json with the list of states of art first followed by the legend
     */
    @RequestMapping("/research/{keyword}")
    public String researchSoa(@PathVariable String keyword) throws IOException {
        keyword = keyword.toLowerCase();
        List<String> keywords = tagSoaService.analyzeKeyWord(keyword);
        if (keywords.isEmpty()){
            return null;
        } else {
            List<TagSoa> listTagSoa = tagSoaService.researchByTagSoa(keywords);
            List<StateOfArt> soa = researchService.researchSoaByTag(listTagSoa, keywords);
            List<StateOfArt> soaUpdate = researchService.soaWithCluster(soa, keywords);
            ResultatResearch resultatResearch = new ResultatResearch(soaUpdate, researchService.clusterService(keywords));
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultatResearch);
            return json;
        }
    }

    /**
     * request to save a research
     * @param keyword
     * @param idUser
     */
    @RequestMapping("/saveResearch/keyword/{keyword}/user/{idUser}")
    public void saveResearch (@PathVariable String keyword, @PathVariable Long idUser){
        researchService.saveResearch(keyword,idUser);
    }

    /**
     * request to get research(history)
     * @param idUser
     */
    @RequestMapping(method= RequestMethod.GET, value = "/saveResearch/keyword/{idUser}")
    public List<Research> getResearch (@PathVariable Long idUser){

        return researchService.getAllResearch(idUser);
    }


}
