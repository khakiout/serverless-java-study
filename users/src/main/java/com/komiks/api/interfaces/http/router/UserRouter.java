package com.komiks.api.interfaces.http.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.komiks.api.domain.entity.User;
import com.komiks.api.interfaces.http.handler.UserHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class UserRouter {

    private final UserHandler userHandler;

    public UserRouter(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    /**
     * User router mapping.
     *
     * @return the router functions.
     */
    public RouterFunction<ServerResponse> getMapping() {
        return RouterFunctions
            .route(GET("/users"), userHandler::getUsers)
            .andRoute(GET("/users/{username}"), userHandler::getUser);
    }

}
