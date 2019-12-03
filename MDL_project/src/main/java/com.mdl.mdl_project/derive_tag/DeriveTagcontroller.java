package com.mdl.mdl_project.derive_tag;

import com.mdl.mdl_project.art.tag.TagSoa;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class DeriveTagcontroller {

    @RequestMapping(method = RequestMethod.POST, value ="/Derive")
    public ArrayList<TagSoa> derive(@RequestBody Derive tags){

        //This part of code is to check if differents articles have some same key words (removing uppercase and space)
        Map<String, Long> derived_tags  = new HashMap<>();
         boolean find;
         Long get_actual_value;

        for (int i = 0; i < tags.size() ; i++) {
            find = false;
            for (int j = 0; j < derived_tags.size(); j++) {

                if ((tags.get(i).replaceAll("\\s+", "").toUpperCase()).equals(derived_tags.keySet().toArray()[j].toString().replaceAll("\\s+", "").toUpperCase())) {
                    get_actual_value = derived_tags.get(derived_tags.keySet().toArray()[j].toString());
                    get_actual_value++;
                    derived_tags.put(derived_tags.keySet().toArray()[j].toString(), get_actual_value);
                    find = true;
                }

            }
            if (!find) {
                derived_tags.put(tags.get(i), new Long(1));
            }

        }



        //This part of code is to calculate the proportion of a word being seen in articles
        Map<String, Float> sorted_percent = new HashMap<>();
        float percent;
        float transit1;
        float transit2;

        for (int i = 0; i < derived_tags.size() ; i++) {
            transit1 = derived_tags.get(derived_tags.keySet().toArray()[i].toString());
            transit2 = tags.size();

            percent = transit1/transit2;
            sorted_percent.put(derived_tags.keySet().toArray()[i].toString(),percent );

        }


        ArrayList<TagSoa> five_derived_tags = new ArrayList<>();
        TagSoa newtag;

        for (int i = 0; i <5 ; i++) {
            if (i+1 <= derived_tags.size()){
                newtag = new TagSoa();
                newtag.setName(sorted_percent.keySet().toArray()[i].toString());
                newtag.setValue(sorted_percent.get(derived_tags.keySet().toArray()[i].toString()));
                five_derived_tags.add(newtag);
            }
        }


        return five_derived_tags;
    }

}
