package com.example.AnimalShelter.Exceptions;

public class UsernameAlreadyInUseException extends Exception {
    public UsernameAlreadyInUseException(String errorMessage) {
        super(errorMessage);
    }
}
