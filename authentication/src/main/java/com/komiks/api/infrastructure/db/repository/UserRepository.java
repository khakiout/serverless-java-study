package com.komiks.api.infrastructure.db.repository;

import com.komiks.api.infrastructure.db.model.User;

public interface UserRepository {

    void saveUser(User user);

    User getUser(String username);

}
