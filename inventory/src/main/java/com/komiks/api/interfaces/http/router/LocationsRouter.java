package com.komiks.api.interfaces.http.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.komiks.api.interfaces.http.handler.LocationHandler;
import com.komiks.api.interfaces.http.handler.SampleHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class LocationsRouter {

    /**
     * Sample router mapping.
     */
    public RouterFunction<ServerResponse> sampleRouter(LocationHandler locationHandler) {
        return RouterFunctions
            .route(GET("/locations/ncr"), locationHandler::getMessage);
    }

}
