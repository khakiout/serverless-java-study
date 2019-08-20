package com.komiks.api.application;

import com.komiks.api.domain.Session;
import com.komiks.api.infrastructure.db.model.User;
import com.komiks.api.infrastructure.db.repository.UserRepository;
import com.komiks.api.model.request.LoginRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles the authentication process and generation of token.
 */
public class AuthenticationApplication {

    private static final Logger logger = LogManager.getLogger(AuthenticationApplication.class);

    private final UserRepository userRepository;

    /**
     * Initialize the authentication application.
     *
     * @param userRepository the user repository implementation.
     */
    public AuthenticationApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
        createUser("admin1", "admin");
        createUser("admin2", "admin");
        createUser("admin3", "admin");
    }

    /**
     * Crude implementation of user login. In real life scenarios, the password should be
     * salted/hashed.
     *
     * @param loginRequest the login request.
     * @return the Session, if the loginRequest is valid.
     */
    public Session authenticate(LoginRequest loginRequest) {
        User user = this.userRepository.getUser(loginRequest.username);
        Session session = null;

        if (user != null && user.getPassword().equals(loginRequest.password)) {
            logger.info("User is valid.");
            session = new Session();
            session.setUsername(user.getUsername());
            session.setToken("12312312312312321");
        } else {
            logger.info("User details is not valid.");
        }

        return session;
    }

    private void createUser(String username, String password) {
        if (this.userRepository.getUser(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            this.userRepository.saveUser(user);
        }
    }
}
