package com.komiks.api.domain.validation.response;

import java.util.ArrayList;
import java.util.List;

public class ValidationReport {

    private static final String TYPE = "ValidationError";
    private List<ValidationErrorItem> details;

    public ValidationReport() {
        this.details = new ArrayList<>();
    }

    public void addError(ValidationErrorItem errorItem) {
        this.details.add(errorItem);
    }

    public String getType() {
        return TYPE;
    }

    public List<ValidationErrorItem> getDetails() {
        return details;
    }
}
