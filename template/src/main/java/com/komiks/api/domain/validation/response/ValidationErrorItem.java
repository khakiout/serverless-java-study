package com.komiks.api.domain.validation.response;

public class ValidationErrorItem {

    private String path;
    private String message;

    private ValidationErrorItem() {

    }

    public static ValidationErrorItem create(String errorMsg) {
        return new ValidationErrorItem().setErrorMessage(errorMsg);
    }

    public ValidationErrorItem setErrorMessage(String message) {
        this.message = message;
        return this;
    }

    public ValidationErrorItem setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
