package com.komiks.api.interfaces.http.models.response;

/**
 * Default Unauthorized 401 response object.
 */
public class GenericUnauthorizedResponse extends BaseResponse {

    public GenericUnauthorizedResponse() {
        super(401, "Unauthorized");
        this.setMessage("You are not authorized to perform this action.");
    }
}
