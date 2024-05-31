import java.util.*;

// Assignment class representing an assignment
class Assignment {
    private String name;
    private String description;
    private Date dueDate;
    // Other properties

    public Assignment(String name, Date dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    // Methods to manage assignment details
    // Getters
    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }
}