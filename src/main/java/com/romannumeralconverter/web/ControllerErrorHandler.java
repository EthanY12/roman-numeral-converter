package com.romannumeralconverter.web;

import com.romannumeralconverter.error.InvalidRomanNumeralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ControllerErrorHandler {
    
    @ExceptionHandler(InvalidRomanNumeralException.class)
    public ResponseEntity<String> handleInvalidParameters(InvalidRomanNumeralException e){
        return new ResponseEntity<>(BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerError(Exception e){
        return new ResponseEntity<>("Internal error: " , INTERNAL_SERVER_ERROR);
    }
}
