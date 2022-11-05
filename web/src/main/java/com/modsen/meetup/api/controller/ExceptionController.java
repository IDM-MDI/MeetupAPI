package com.modsen.meetup.api.controller;

import com.modsen.meetup.api.exception.PersistenceException;
import com.modsen.meetup.api.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<String> serviceException(ServiceException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(PersistenceException.class)
    public final ResponseEntity<String> repositoryException(PersistenceException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }
}
