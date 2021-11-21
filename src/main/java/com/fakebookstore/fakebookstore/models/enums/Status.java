package com.fakebookstore.fakebookstore.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {

    @JsonProperty("Active")
    ACTIVE,
    @JsonProperty("Unavailable")
    UNAVAILABLE;

    Status() {

    }

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
