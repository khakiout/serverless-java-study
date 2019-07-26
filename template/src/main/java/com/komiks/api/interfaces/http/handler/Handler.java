package com.khakiout.api.interfaces.http.handler;

import com.khakiout.api.interfaces.http.models.response.GenericBadRequestResponse;
import com.khakiout.api.interfaces.http.models.response.GenericForbiddenResponse;
import com.khakiout.api.interfaces.http.models.response.GenericMessageResponse;
import com.khakiout.api.interfaces.http.models.response.NotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Base methods for the handler classes.
 */
public abstract class Handler {

    /**
     * Return the generic 404 response for missing entities.
     *
     * @return the 404 response.
     */
    protected Mono<ServerResponse> returnNotFound() {
        return ServerResponse.status(HttpStatus.NOT_FOUND).body(Mono.just(new NotFoundResponse()),
            NotFoundResponse.class);
    }

    /**
     * Return a generic 400 bad request response.
     *
     * @return the 400 response.
     */
    protected Mono<ServerResponse> returnGenericBadRequest() {
        return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(new GenericBadRequestResponse()),
                GenericBadRequestResponse.class);
    }

    /**
     * Return a generic 403 forbidden response.
     *
     * @return the 403 response.
     */
    protected Mono<ServerResponse> returnForbiddenResponse() {
        return ServerResponse.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(new GenericForbiddenResponse()),
                GenericForbiddenResponse.class);
    }

    /**
     * Return a generic 200 message response.
     * @param message the content message.
     *
     * @return the 200 response.
     */
    protected Mono<ServerResponse> returnGenericMessageResponse(String message) {
        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(new GenericMessageResponse(message)),
                GenericMessageResponse.class);
    }

}
