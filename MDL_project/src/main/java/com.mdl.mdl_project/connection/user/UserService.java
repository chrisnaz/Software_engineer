package com.mdl.mdl_project.connection.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * Return All State Of Art
     */
    public List<User> getAllUsers(){

        //return  stateofarts;
        List<User> users = new ArrayList<>();
        userrepository.findAll().forEach(users :: add); // lambda expression

        return users;
    }

    /*
     * Return one State Of Art in terms of its ID
     */
    public User getUser(Long id){
        //return stateofarts.stream().filter(t -> t.getId() == id ).findFirst().get();
        return userrepository.findById(id).orElse(null);
    }


    /*
     * Return one State Of Art in terms of its username
     */
    public User getUserName(String username){
        //return stateofarts.stream().filter(t -> t.getId() == id ).findFirst().get();
        return userrepository.findByUsername(username).orElse(null);
    }

    /*
     * Add one State Of Art
     */
    public void addUser(User userdto) throws EmailExistsException, UsernameExistsException, WrongPassword {
        List<User> allusers = new ArrayList<>();
        userrepository.findAll().forEach(allusers :: add); // lambda expression

        if (!allusers.isEmpty()) {
            for (int i = 0; i < allusers.size(); i++) {
                if (allusers.get(i).getEmail().equals(userdto.getEmail())) {
                    throw new EmailExistsException(
                            "There is an account with that email adress:" + userdto.getEmail());
                }else if(allusers.get(i).getUsername().equals(userdto.getUsername())){
                    throw new UsernameExistsException(
                            "There is an account with that username:" + userdto.getUsername());


                }

            }

        }

        if(!userdto.getPassword().equals(userdto.getPasswordConfirm())){
            throw new WrongPassword(
                    "Both passwords are not the same"
            );
        }

        User user = new User();

        user.setUsername(userdto.getUsername());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setEmail(userdto.getEmail());
        user.setEstablishment(userdto.getEstablishment());
        user.setFirstname(userdto.getFirstname());
        user.setLastname(userdto.getLastname());
        user.setStatut(userdto.getStatut());
        user.setRole( "ROLE_USER");

        userrepository.save(user);
    }

    /*
     * Update one State Of Art in terms of its ID
     */
    public void updateUser(Long id, User user) {
       /* for (int i = 0; i < stateofarts.size(); i++) {
            if (stateofarts.get(i).getId() == id) {
                stateofarts.set(i, stateofart);
                return;
            }
        }*/
        userrepository.save(user);
    }

    /*
     * Remove one State Of Art in terms of its ID
     */
    public void deleteUser(Long id){
        //stateofarts.removeIf(t -> t.getId() == id);
        userrepository.deleteById(id);

    }


}
