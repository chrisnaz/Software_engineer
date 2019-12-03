package com.mdl.mdl_project.connection.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@Controller
public class UserController {

    @Autowired
    private UserService userservice;

    //PasswordEncoder passwordEncoder;


    @RequestMapping("/Users")
    public List<User> getUsers(){
        return userservice.getAllUsers();
    }

    @RequestMapping("/Users/{id}")
    public User getUser(@PathVariable Long id){
        return userservice.getUser(id);
    }

    @RequestMapping("/UsersName/{username}")
    public User getUserName(@PathVariable String username){
        return userservice.getUserName(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Users")
    public void addUser( @RequestBody User user) throws EmailExistsException, UsernameExistsException, WrongPassword{
        //user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userservice.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Users/{id}")
    public void updateUser( @RequestBody User user,@PathVariable Long id ){
        userservice.updateUser(id,user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Users/{id}")
    public void deleteUser( @RequestBody User user,@PathVariable Long id ){
        userservice.deleteUser(id);
    }

    @GetMapping("/private")
    public String privateArea(){
        return "Private";
    }



}
