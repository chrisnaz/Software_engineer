package com.mdl.mdl_project;

import com.mdl.mdl_project.connection.user.User;
import com.mdl.mdl_project.connection.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {



    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {


        this.users.save(User.builder()
                .username("user")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList( "ROLE_USER"))
                .email("test@gmail.com")
                .firstname("Maxime")
                .statut("Student")
                .lastname("Dalla Valle")
                .establishment("UNamur")
                .statut("Student")
                .build()
        );

        this.users.save(User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_ADMIN", "ROLE_USER"))
                .email("admin@me.com")
                .firstname("John")
                .lastname("Root")
                .statut("Researcher")
                .establishment("UNamur")
                .build()
        );

        System.out.println("printing all users...");
        this.users.findAll().forEach(v -> System.out.println((" User :" + v.toString())));
    }
}