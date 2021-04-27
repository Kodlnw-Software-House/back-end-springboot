package com.int221.finalproject.exceptions;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleExceptions(CustomException customException, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(customException.getErrorCode(),customException.getMessage(), LocalDateTime.now());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }

    @ExceptionHandler(NotFoundException.class)
    public String sss(){
        return "sdasda";
    }
}
