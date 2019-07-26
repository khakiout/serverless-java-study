package com.khakiout.api.interfaces.http.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * A sample handler.
 */
public class SampleHandler extends Handler {

    public Mono<ServerResponse> getMessage(ServerRequest request) {
        return this.returnGenericMessageResponse("Hello");
    }

}
