package com.fakebookstore.fakebookstore.services.categorysuccessmethods;

import com.fakebookstore.fakebookstore.models.Category;

public class UpdateSuccess {

    private String message;
    private Category success;

    public UpdateSuccess(String message, Category success) {

        this.message = message;
        this.success = success;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Category getSuccess() {
        return success;
    }

    public void setSuccess(Category success) {
        this.success = success;
    }

}
