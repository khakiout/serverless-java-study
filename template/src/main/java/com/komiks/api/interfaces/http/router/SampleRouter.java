package com.komiks.api.interfaces.http.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.komiks.api.interfaces.http.handler.SampleHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class SampleRouter {

    /**
     * Sample router mapping.
     *
     * @param sampleHandler the handler needed for this sample.
     * @return the router functions.
     */
    public RouterFunction<ServerResponse> sampleRouter(SampleHandler sampleHandler) {
        return RouterFunctions
            .route(GET("/template/home"), sampleHandler::getMessage)
            .andRoute(GET("/template/message"), sampleHandler::getMessage);
    }

}
