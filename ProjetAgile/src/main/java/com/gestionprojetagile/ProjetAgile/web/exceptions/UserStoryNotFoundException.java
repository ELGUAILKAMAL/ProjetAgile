package com.gestionprojetagile.ProjetAgile.web.exceptions;

public class UserStoryNotFoundException extends RuntimeException {
    public UserStoryNotFoundException(String message) {
        super(message);
    }
}
