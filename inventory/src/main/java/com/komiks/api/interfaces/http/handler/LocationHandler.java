package com.komiks.api.interfaces.http.handler;

import com.komiks.api.application.LocationsApplication;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * The locations handler.
 */
public class LocationHandler extends Handler {

    private Logger logger = LoggerFactory.getLogger(LocationHandler.class);

    final LocationsApplication locationsApplication;

    public LocationHandler(LocationsApplication locationsApplication) {
        this.locationsApplication = locationsApplication;
    }

    public Mono<ServerResponse> getMessage(ServerRequest request) {
        try {
            logger.info("Getting directory");
            return ServerResponse.ok()
                .body(Mono.just(this.locationsApplication.getDirectory()),
                    ParameterizedTypeReference
                        .forType(HashMap.class));
        } catch (Exception e) {
            logger.error("ERROR", e);
            return returnGenericMessageResponse("oops");
        }
    }

}
