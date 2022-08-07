package com.example.firstspringbootapp.exception;

public class LevelNotFoundException extends RuntimeException {
    public LevelNotFoundException(String message) {
        super(message);
    }
}
