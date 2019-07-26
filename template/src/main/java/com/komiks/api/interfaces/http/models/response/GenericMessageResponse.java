package com.khakiout.api.interfaces.http.models.response;

/**
 * Generic bad request response.
 */
public class GenericMessageResponse extends BaseResponse {

    public final String message;

    public GenericMessageResponse(String message) {
        super("Message");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
