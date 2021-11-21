package com.fakebookstore.fakebookstore.services.booksuccessmethods;

import com.fakebookstore.fakebookstore.models.Book;

import java.util.List;

public class ReadSuccess {

    private String message;
    private List<Book> success;

    public ReadSuccess(String message, List<Book> success) {

        this.message = message;
        this.success = success;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Book> getSuccess() {
        return success;
    }

    public void setSuccess(List<Book> success) {
        this.success = success;
    }

}
