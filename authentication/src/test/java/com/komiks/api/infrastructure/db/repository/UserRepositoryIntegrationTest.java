package com.komiks.api.infrastructure.db.repository;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.komiks.api.infrastructure.db.model.User;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryIntegrationTest {

    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void testSaving() {
        User user = new User();
        user.setPassword("reun");
        user.setUsername("tesdasdsat");

        userRepository.saveUser(user);
    }

    @Test
    public void testFetchByUsername() {
        User user = userRepository.getUser("test");

        assertNotNull(user);
        assertEquals("test", user.getUsername());
    }
}
