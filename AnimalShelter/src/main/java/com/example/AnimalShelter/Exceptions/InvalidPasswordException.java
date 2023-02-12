package com.example.AnimalShelter.Exceptions;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
