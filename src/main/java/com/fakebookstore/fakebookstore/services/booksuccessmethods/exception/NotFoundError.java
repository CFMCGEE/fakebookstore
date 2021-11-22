package com.fakebookstore.fakebookstore.services.booksuccessmethods.exception;

public class NotFoundError {

    private String message;

    public NotFoundError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
