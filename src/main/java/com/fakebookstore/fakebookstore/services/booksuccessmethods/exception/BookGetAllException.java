package com.fakebookstore.fakebookstore.services.booksuccessmethods.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookGetAllException extends RuntimeException {

    public BookGetAllException() {

    }

    public BookGetAllException(String message) {
        super(message);
    }

    public BookGetAllException(String message, Throwable cause) {
        super(message, cause);
    }

}
