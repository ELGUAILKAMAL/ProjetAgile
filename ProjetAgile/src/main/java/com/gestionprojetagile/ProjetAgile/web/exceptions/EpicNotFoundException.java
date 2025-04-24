package com.gestionprojetagile.ProjetAgile.web.exceptions;

public class EpicNotFoundException extends RuntimeException {
    public EpicNotFoundException(String message) {
        super(message);
    }
}
