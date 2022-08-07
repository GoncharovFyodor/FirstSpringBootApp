package com.example.firstspringbootapp.advice;

import com.example.firstspringbootapp.exception.*;
import com.example.firstspringbootapp.payload.response.MessageError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(AnswerNotFoundException e){
        MessageError messageError = new MessageError();
        messageError.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }
    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(LevelNotFoundException e){
        MessageError messageError = new MessageError();
        messageError.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }
    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(QuestionNotFoundException e){
        MessageError messageError = new MessageError();
        messageError.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }
    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(QuestionExistException e){
        MessageError messageError = new MessageError();
        messageError.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }
    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(AnswerQuantityMismatchException e){
        MessageError messageError = new MessageError();
        messageError.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }
    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(ProfileNotFoundException e){
        MessageError messageError = new MessageError();
        messageError.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }
    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(SQLException e){
        MessageError messageError = new MessageError();
        messageError.setMessage("Incorrect data");
        return ResponseEntity.badRequest().body(messageError);
    }
    @ExceptionHandler
    public ResponseEntity<Map<String,String>> exceptionHandler(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors()
                .forEach((error)->{
                    String fieldName = ((FieldError) error).getField();
                    String defaultMessage = error.getDefaultMessage();
                    errors.put(fieldName,defaultMessage);
                });
        return ResponseEntity.badRequest().body(errors);
    }
}
