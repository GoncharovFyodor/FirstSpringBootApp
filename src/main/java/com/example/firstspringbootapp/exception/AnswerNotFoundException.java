package com.example.firstspringbootapp.exception;

public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException(String message) {
        super(message);
    }
}
