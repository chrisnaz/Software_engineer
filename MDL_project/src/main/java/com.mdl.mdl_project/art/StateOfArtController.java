package com.mdl.mdl_project.art;

import com.mdl.mdl_project.art.history.History;
import com.mdl.mdl_project.art.history.HistoryRepository;
import com.mdl.mdl_project.connection.user.User;
import com.mdl.mdl_project.connection.user.UserRepository;
import com.mdl.mdl_project.connection.user.UsernameExistsException;
//import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class StateOfArtController {
    

    @Autowired
    private StateOfArtService stateoftheartservice;

    @Autowired
    UserRepository userrepository;

    @Autowired
    private HistoryRepository historyRepository;


    /*
     *Get All SOA by User
     */
    @RequestMapping(method= RequestMethod.GET, value = "/Users/{UsersId}/StateOfArts")
    public List<StateOfArt> getAllStateOfArtsByUser(@PathVariable Long UsersId){
        return stateoftheartservice.getAllStateOfArtsByUser(UsersId);
    }

    /*
     *Get All SOA
     */
    @RequestMapping("/StateOfArts")
    public List<StateOfArt> getAllStateOfArts(){
        return stateoftheartservice.getAllStateOfArts();
    }

    /*
     *Get All SOA by ID
     */
    @RequestMapping("/StateOfArts/{id}")
    public StateOfArt getStateOfArt(@PathVariable Integer id){
        return stateoftheartservice.getStateOfArt(id);
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/StateOfArts")
    public void addStateOfArt( @RequestBody StateOfArt stateofart){
        stateoftheartservice.addStateOfTheArt(stateofart);
    }*/

    /*
     *Post a new SOA (need the user ID to know the creator)
     */
    @RequestMapping(method = RequestMethod.POST, value = "/Users/{UsersId}/StateOfArts")
    public Integer addStateOfArt( @RequestBody StateOfArt stateofart , @PathVariable Long UsersId) throws IdNotFoundException{
        List<User> userslist  = new ArrayList<>();
        userrepository.findAll().forEach(userslist :: add);

        boolean find = false;
        for (int i = 0; i < userslist.size(); i++) {
            if(userslist.get(i).getId() == UsersId){
                find = true;
                stateofart.setUser(new User(UsersId,null,null,"","","","","","",null));

            }

        }
        if (!find){
            throw new IdNotFoundException(
                    "There is no account with that id: " + UsersId);
        }
        LocalDateTime ldt = LocalDateTime.now();
        stateofart.setLastModified(ldt);
        stateoftheartservice.addStateOfTheArt(stateofart);
        historyRepository.save(new History(ldt,null, "creation", stateofart.getId()));

        return stateofart.getId();
    }

    /*
     *Modify SOA by ID
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/StateOfArts/{id}")
    public void updateStateOfArt( @RequestBody StateOfArt stateofart,@PathVariable Integer id ){
        stateoftheartservice.updateStateOfTheArt(id,stateofart);
    }

    /*@RequestMapping(method = RequestMethod.PUT, value = "/Users/{UsersId}/StateOfArts/{id}")
    public void updateStateOfArt( @RequestBody StateOfArt stateofart ,@PathVariable Long UsersId, @PathVariable Integer id  ){
        stateofart.setUser(new User(UsersId,null,null,"","","","","","",null));
        stateoftheartservice.updateStateOfTheArt(id,stateofart);
    }*/

    /*
     *Delete SOA by ID
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/StateOfArts/{id}")
    public void deleteStateOfArt( @RequestBody StateOfArt stateofart,@PathVariable Integer id ){
        historyRepository.save(new History(LocalDateTime.now(),null, "deletion",stateofart.getId()));
        stateoftheartservice.deleteStateOfTheArt(id);
    }


}