package com.mdl.mdl_project.art.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagSoaService {

    @Autowired
    private TagSoaRepository tagSoaRepository;

    /**
     * This function allow to create several keyword from words of search
     * @param keyword, enter by user
     * @return list keyword possible
     */
    public List<String> analyzeKeyWord (String keyword){
        List<String> keywords = new ArrayList<>();
        if (keyword.isEmpty())
            return keywords;
        String[] words = keyword.split(" ");
        keywords.add(keyword);
        if (words.length < 2)
            return keywords;
        for(int i=0; i<words.length; i++)
            keywords.add(words[i]);
        return keywords;
    }

    /**
     * This function search tags with keyword which match to research
     * @param keywords, list keyword
     * @return list Tag
     */
    public List<TagSoa> researchByTagSoa (List<String> keywords){
        List<TagSoa> listTagSoa = new ArrayList<>();
        if (keywords.isEmpty())
            return listTagSoa;
        for(int i=0; i<keywords.size(); i++){
            tagSoaRepository.findAllByName(keywords.get(i)).forEach(listTagSoa :: add);
        }
        return listTagSoa;
    }

}
