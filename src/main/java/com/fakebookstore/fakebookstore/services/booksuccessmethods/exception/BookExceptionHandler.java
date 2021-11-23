package com.fakebookstore.fakebookstore.services.booksuccessmethods.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler extends BookGetAllException {

    @ExceptionHandler(BookGetAllException.class)
    public ResponseEntity<?> handleBookException() {
        NotFoundError error = new NotFoundError("No books found.");
        return new ResponseEntity<>(error, null, HttpStatus.NOT_FOUND);
    }

}
