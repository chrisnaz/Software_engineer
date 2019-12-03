package com.mdl.mdl_project.art;


import com.mdl.mdl_project.article.Article;
import com.mdl.mdl_project.connection.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StateOfArtService {

    @Autowired
    private StateOfArtRepository stateofartrepository;



    /*
     * Return All State Of Art of an user
     */
    public List<StateOfArt> getAllStateOfArtsByUser(Long userid){
        List<StateOfArt> stateofarts = new ArrayList<>();
        stateofartrepository.findByUserId(userid).forEach(stateofarts :: add); // lambda expression

        //initiate an user for the loop
        User theuser;

        //Remove password from get
        for (int i = 0; i <stateofarts.size() ; i++) {
            theuser = new User(stateofarts.get(i).getUser().getId(),stateofarts.get(i).getUser().getUsername(),null,null,stateofarts.get(i).getUser().getFirstname(),stateofarts.get(i).getUser().getLastname(),stateofarts.get(i).getUser().getEstablishment(),stateofarts.get(i).getUser().getEmail(),stateofarts.get(i).getUser().getStatut(),stateofarts.get(i).getUser().getRoles());
            stateofarts.get(i).setUser(theuser);

        }


        return stateofarts;
    }

    /*
     * Return All State Of Art
     */
    public List<StateOfArt> getAllStateOfArts(){

        //return  stateofarts;
        List<StateOfArt> stateofarts = new ArrayList<>();
        stateofartrepository.findAll().forEach(stateofarts :: add); // lambda expression

        //initiate an user for the loop
        User theuser;

        //Remove password from get
        for (int i = 0; i <stateofarts.size() ; i++) {
            theuser = new User(stateofarts.get(i).getUser().getId(),stateofarts.get(i).getUser().getUsername(),null,null,stateofarts.get(i).getUser().getFirstname(),stateofarts.get(i).getUser().getLastname(),stateofarts.get(i).getUser().getEstablishment(),stateofarts.get(i).getUser().getEmail(),stateofarts.get(i).getUser().getStatut(),stateofarts.get(i).getUser().getRoles());
            stateofarts.get(i).setUser(theuser);

        }


        return stateofarts;
    }

    /*
     * Return one State Of Art in terms of its ID
     */
    public StateOfArt getStateOfArt(Integer id){
        //return stateofarts.stream().filter(t -> t.getId() == id ).findFirst().get();
        StateOfArt stateofarts = stateofartrepository.findById(id).orElse(null);
        //initiate an user for the loop
        User theuser;

        //Remove password from get
        theuser = new User(stateofarts.getUser().getId(),stateofarts.getUser().getUsername(),null,null,stateofarts.getUser().getFirstname(),stateofarts.getUser().getLastname(),stateofarts.getUser().getEstablishment(),stateofarts.getUser().getEmail(),stateofarts.getUser().getStatut(),stateofarts.getUser().getRoles());
        stateofarts.setUser(theuser);


        return stateofarts;
    }

    /*
     * Add one State Of Art
     */
    public void addStateOfTheArt(StateOfArt stateofart){
        //stateofarts.add(stateofart);

        stateofartrepository.save(stateofart);
    }

    /*
     * Update one State Of Art in terms of its ID
     */
    public void updateStateOfTheArt(Integer id, StateOfArt stateofart) {
        List<StateOfArt> stateofarts = new ArrayList<>();
        stateofartrepository.findAll().forEach(stateofarts :: add);

        for (int i = 0; i < stateofarts.size(); i++) {
            if (stateofarts.get(i).getId() == id) {

                //stateofarts.get(i).setDate(stateofart.getDate());
                //stateofarts.get(i).setContribution(stateofart.getContribution());
                stateofarts.get(i).setTags(stateofart.getTags());
                //stateofarts.get(i).setAuthors(stateofart.getAuthors());
                //stateofarts.get(i).setTheabstract(stateofart.getTheabstract());

                stateofartrepository.save(stateofarts.get(i));
            }
        }
        //stateofartrepository.save(stateofart);
    }

    /*
     * Remove one State Of Art in terms of its ID
     */
    public void deleteStateOfTheArt(Integer id){
        //stateofarts.removeIf(t -> t.getId() == id);
        stateofartrepository.deleteById(id);

    }


}