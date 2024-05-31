import java.util.*;

// User class representing both students and staff
class User {
    private String username;
    private String password;
    private Date lastActivityTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.lastActivityTime = new Date();
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastActivityTime() {
        return lastActivityTime;
    }

    public void updateActivityTime() {
        this.lastActivityTime = new Date();
    }

    // Method to logout user
    public void logout() {
        System.out.println("Logging out user: " + username);
        // Additional logout actions can be added here
    }
}

