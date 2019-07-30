package com.komiks.api.interfaces.http.models.response;

/**
 * Default 404 response object.
 */
public class NotFoundResponse extends BaseResponse {

    public final String details = "Entity not found.";

    public NotFoundResponse() {
        super("NotFound");
    }
}
