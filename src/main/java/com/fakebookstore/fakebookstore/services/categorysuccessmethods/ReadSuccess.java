package com.fakebookstore.fakebookstore.services.categorysuccessmethods;

import com.fakebookstore.fakebookstore.models.Category;

import java.util.List;

public class ReadSuccess {

    private String message;
    private List<Category> success;

    public ReadSuccess(String message, List<Category> success) {

        this.message = message;
        this.success = success;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Category> getSuccess() {
        return success;
    }

    public void setSuccess(List<Category> success) {
        this.success = success;
    }

}
