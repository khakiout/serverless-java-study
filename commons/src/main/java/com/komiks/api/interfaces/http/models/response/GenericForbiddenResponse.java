package com.komiks.api.interfaces.http.models.response;

/**
 * Default Forbidden 403 response object.
 */
public class GenericForbiddenResponse extends BaseResponse {

    public GenericForbiddenResponse() {
        super(403, "Forbidden");
        this.setMessage("You are not authorized to perform this action.");
    }
}
