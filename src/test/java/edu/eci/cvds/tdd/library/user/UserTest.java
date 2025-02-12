package edu.eci.cvds.tdd.library.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    
    @Test
    void testUserCreation() {
        User user = new User();
        user.setId("12345");
        user.setName("John Doe");
        assertEquals("12345", user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    void testUserSetters() {
        User user = new User();
        user.setId("67890");
        user.setName("Alice");
        assertEquals("67890", user.getId());
        assertEquals("Alice", user.getName());
    }
}