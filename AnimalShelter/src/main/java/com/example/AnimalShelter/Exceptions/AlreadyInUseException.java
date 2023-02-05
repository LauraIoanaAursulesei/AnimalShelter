package com.example.AnimalShelter.Exceptions;

public class AlreadyInUseException extends Exception {
    public AlreadyInUseException(String errorMessage) {
        super(errorMessage);
    }
}
