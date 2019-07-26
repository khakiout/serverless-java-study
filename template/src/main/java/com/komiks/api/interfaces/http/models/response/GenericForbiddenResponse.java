package com.khakiout.api.interfaces.http.models.response;

/**
 * Default Forbidden 403 response object.
 */
public class GenericForbiddenResponse extends BaseResponse {

    public final String details = "You are not authorized to perform this action.";

    public GenericForbiddenResponse() {
        super("Forbidden");
    }
}
