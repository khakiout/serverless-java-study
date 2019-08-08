package com.komiks.api.application;

import com.komiks.api.application.models.PagedResult;
import com.komiks.api.domain.entity.Role;
import com.komiks.api.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Mono;

public class UserApplication {

    /**
     * Retrieve a user with the username submitted.
     *
     * @param username The username of the user to be retrieved.
     * @return The Mono containing the user if exists.
     */
    public Mono<User> getUser(String username) {
        User user = this.createUser(username);

        return Mono.just(user);
    }

    /**
     * Retrieve the set of users.
     *
     * @param page the current page.
     * @param count the total count.
     *
     * @return The pagedResult of the query.
     */
    public Mono<PagedResult<User>> getUsers(String page, String count) {
        List<User> users = new ArrayList<>();
        int total = Integer.parseInt(count);
        for (int i = 0; i < total; i++) {
            User user = this.createUser("user" + (i + 1));
            users.add(user);
        }

        PagedResult<User> pagedResult = new PagedResult<>();
        pagedResult.setResults(users);
        PagedResult.PageMetadata metadata = pagedResult.getMetadata();
        metadata.setCurrentPage("1");
        metadata.setNextPage("2");
        metadata.setPreviousPage("0");
        metadata.setTotalCount(count);
        metadata.setResultCount(count);

        return Mono.just(pagedResult);
    }

    private User createUser(String username) {
        User user = new User();
        user.setAlias("Monday Artist");
        user.setName("Mona Artista");
        user.setUsername(username);
        user.setRole(Role.USER);

        return user;
    }

}
