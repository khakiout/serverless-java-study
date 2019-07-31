package com.komiks.api.interfaces.http.models.response;

/**
 * Base response object.
 */
public abstract class BaseResponse {

    protected final String type;

    BaseResponse(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
