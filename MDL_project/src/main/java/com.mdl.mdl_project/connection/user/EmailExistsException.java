package com.mdl.mdl_project.connection.user;

public class EmailExistsException extends Exception {
    public EmailExistsException (String message){
        super(message);
    }
}
