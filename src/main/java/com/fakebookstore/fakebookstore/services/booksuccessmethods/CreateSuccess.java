package com.fakebookstore.fakebookstore.services.booksuccessmethods;

import com.fakebookstore.fakebookstore.models.Book;

public class CreateSuccess {

    private String message;
    private Book success;

    public CreateSuccess(String message, Book success) {

        this.message = message;
        this.success = success;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getSuccess() {
        return success;
    }

    public void setSuccess(Book success) {
        this.success = success;
    }

}
