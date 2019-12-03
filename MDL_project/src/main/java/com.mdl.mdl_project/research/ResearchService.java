package com.mdl.mdl_project.research;

import com.mdl.mdl_project.art.StateOfArt;
import com.mdl.mdl_project.art.StateOfArtRepository;
import com.mdl.mdl_project.art.tag.TagSoa;
import com.mdl.mdl_project.connection.user.User;
import com.mdl.mdl_project.connection.user.UserRepository;
import com.mdl.mdl_project.research.category.Meaning;
import com.mdl.mdl_project.research.category.ResultatResearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResearchService {

    @Autowired
    private ResearchRepository researchRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     *  To save a research
     * @param the word typed in the search bar
     * @param idUser
     */
    public void saveResearch(String keyword, Long idUser){
        User user = userRepository.findById(idUser).orElse(null );
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        Research research = new Research(date, keyword, user);
        researchRepository.save(research);
    }

    /**
     * To collect state of arts associated to id Tag
     * @requires For the algorithm to be optical, it recommends not to exceed 6 words in the search
     * @param listTagSoa, list tag
     * @return list state of art
     */
    public List<StateOfArt> researchSoaByTag(List<TagSoa> listTagSoa, List<String> keywords){
        List<StateOfArt> soa = new ArrayList<>();
        List<StateOfArt> buffer; // In case one tag is associated more of one State of art
        Integer indice;
        Integer cluster;
        if(listTagSoa.isEmpty())
            return soa;
        for(int i=0; i<listTagSoa.size(); i++){
            buffer = listTagSoa.get(i).getStateOfArts();
            // Second for is used here if a tag is associated with two Soa
            for (int j=0; j<buffer.size();j++){
                indice = soa.indexOf(buffer.get(j));
                if (indice < 0) {
                    cluster = keywords.indexOf(listTagSoa.get(i).getName()) +1;
                    buffer.get(j).setCluster(cluster);
                    soa.add(buffer.get(j));
                }else {
                    if(soa.get(indice).getCluster() != 1){
                        cluster = soa.get(indice).getCluster() * (keywords.indexOf(listTagSoa.get(i).getName()) +1);
                        soa.get(indice).setCluster(cluster);
                    }
                }
            }
            buffer = null;

        }
        return soa;
    }

    /**
     * Associated each state of art with the cluster corresponding to tue legend
     * @param soa, list state of art
     * @param keywords, list combination keyword
     * @return list of state of art
     */
    public List<StateOfArt> soaWithCluster(List<StateOfArt> soa, List<String> keywords){
        List<Integer> reference = Meaning.referenceCluster(keywords);
        Integer ref;
        for (int i = 0; i < soa.size(); i++){
            ref = reference.indexOf(soa.get(i).getCluster());
            soa.get(i).setCluster(ref);
        }
        return soa;
    }

    /**
     * Creates a legend for the cluster
     * @param keywords, list combination keywords
     * @return
     */
    public List<String> clusterService (List<String> keywords){
        return Meaning.cluster(keywords);
    }

    /*
     * Return All State Of Art of an user
     */
    public List<Research> getAllResearch(Long userid){
        List<Research> researchs = new ArrayList<>();
        researchRepository.findByUserId(userid).forEach(researchs :: add); // lambda expression

        return researchs;
    }

}
