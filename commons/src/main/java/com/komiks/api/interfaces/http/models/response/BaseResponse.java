package com.komiks.api.interfaces.http.models.response;

/**
 * Base response object.
 */
public abstract class BaseResponse {

    protected final int statusCode;
    protected final String type;
    protected String message;

    BaseResponse(int statusCode, String type) {
        this.statusCode = statusCode;
        this.type = type;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getType() {
        return type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
