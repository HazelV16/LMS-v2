import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    @Test
    @DisplayName("Test testGetName()")
    public void testGetName() {
        String name = "Research Report";
        Date dueDate = new Date();
        Assignment assignment = new Assignment(name, dueDate);
        assertEquals(name, assignment.getName());
    }

    @Test
    @DisplayName("Test testGetDueDate()")
    public void testGetDueDate() {
        String name = "Research Report";
        Date dueDate = new Date();
        Assignment assignment = new Assignment(name, dueDate);
        assertEquals(dueDate, assignment.getDueDate());
    }
}