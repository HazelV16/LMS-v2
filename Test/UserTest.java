import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new User("testUser1", "password1");

    @Test
    @DisplayName("Test getUsername() method")
    void getUsername() {
        String userName = user.getUsername();
        assertEquals("testUser1", userName);
    }

    @Test
    @DisplayName("Test getPassword() method")
    void getPassword() {
        String password = user.getPassword();
        assertEquals("password1", password);
    }

    @Test
    @DisplayName("Test getLastActivityTime() method")
    void getLastActivityTime() {
        // Get the last activity time (initially)
        long initialLastActivityTime = user.getLastActivityTime().getTime();

        // Simulate some activity (e.g., updating activity time)
        user.updateActivityTime();

        // Get the last activity time again
        long updatedLastActivityTime = user.getLastActivityTime().getTime();

        // Assert that the updated last activity time is greater than the initial one
        assertTrue(updatedLastActivityTime > initialLastActivityTime);
    }

}