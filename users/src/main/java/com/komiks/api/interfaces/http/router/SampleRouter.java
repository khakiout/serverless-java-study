package com.komiks.api.interfaces.http.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.komiks.api.interfaces.http.handler.UserHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class SampleRouter {

    /**
     * Sample router mapping.
     *
     * @param userHandler the handler needed for this sample.
     * @return the router functions.
     */
    public RouterFunction<ServerResponse> sampleRouter(UserHandler userHandler) {
        return RouterFunctions
            .route(GET("/users/home"), userHandler::getMessage)
            .andRoute(GET("/users/message"), userHandler::getMessage);
    }

}
