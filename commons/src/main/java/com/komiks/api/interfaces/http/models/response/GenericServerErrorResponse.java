package com.komiks.api.interfaces.http.models.response;

/**
 * Generic server error response.
 */
public class GenericServerErrorResponse extends BaseResponse {

    public GenericServerErrorResponse() {
        super(500, "ServerError");
        this.setMessage("Error in server.");
    }

}
