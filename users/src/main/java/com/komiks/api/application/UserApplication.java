package com.komiks.api.application;

import com.komiks.api.domain.entity.Role;
import com.komiks.api.domain.entity.User;
import reactor.core.publisher.Mono;

public class UserApplication {

    /**
     * Retrieve a user with the username submitted.
     *
     * @param username The username of the user to be retrieved.
     * @return The Mono containing the user if exists.
     */
    public Mono<User> getUser(String username) {
        User user = new User();
        user.setAlias("Monday Artist");
        user.setName("Mona Artista");
        user.setUsername(username);
        user.setRole(Role.USER);

        return Mono.just(user);
    }

}
