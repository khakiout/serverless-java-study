package com.komiks.api.interfaces.http.models.response;

/**
 * Default 404 response object.
 */
public class NotFoundResponse extends BaseResponse {

    public NotFoundResponse() {
        super(404, "NotFound");
        this.setMessage("Resource not found.");
    }
}
