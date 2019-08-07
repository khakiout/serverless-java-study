package com.komiks.api.interfaces.http.handler;

import com.komiks.api.application.UserApplication;
import com.komiks.api.domain.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * User handler.
 */
public class UserHandler extends Handler {

    private final UserApplication userApplication;

    public UserHandler(UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    /**
     * Retrieve the user using the username parameter.
     *
     * @param request the server request
     * @return the server response with the user entity.
     */
    public Mono<ServerResponse> getUser(ServerRequest request) {
        String username = request.pathVariable("username");
        Mono<User> user = this.userApplication.getUser(username);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user, User.class);
    }

}
