package com.example.firstspringbootapp.exception;

public class QuestionExistException extends RuntimeException {
    public QuestionExistException(String message) {
        super(message);
    }
}
