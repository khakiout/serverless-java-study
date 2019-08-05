package com.komiks.api.interfaces.http.models.response;

/**
 * Generic bad request response.
 */
public class GenericBadRequestResponse extends BaseResponse {

    public GenericBadRequestResponse() {
        super(400, "BadRequest");
        this.setMessage("Bad request");
    }

}
