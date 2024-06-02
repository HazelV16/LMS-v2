import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    @DisplayName("C01: Test getCourseCode")
    void getCourseCode() {
        Course course = new Course("CS103");
        assertEquals("CS103", course.getCourseCode());
    }

    @Test
    @DisplayName("C02: Test courseCreation()")
    void courseCreation() {
        Course course = new Course("CS103");
        assertEquals("CS103", course.getCourseCode());
        assertNotNull(course.getAssignments());
        assertTrue(course.getAssignments().isEmpty());
        assertNotNull(course.getExamSchedules());
        assertTrue(course.getExamSchedules().isEmpty());
    }

    @Test
    @DisplayName("C03: Test getAssignments()")
    void getAssignments() {
        Course course = new Course("CS103");
        Assignment assignment1 = new Assignment("Assignment 1", new Date());
        Assignment assignment2 = new Assignment("Assignment 2", new Date());

        List<Assignment> assignments = course.getAssignments();
        assertNotNull(assignments);
        assertTrue(assignments.isEmpty());

        // Add assignments
        course.addAssignment(assignment1);
        course.addAssignment(assignment2);

        assignments = course.getAssignments();
        assertNotNull(assignments);
        assertEquals(2, assignments.size());
        assertEquals(assignment1, assignments.get(0));
        assertEquals(assignment2, assignments.get(1));
    }

    @Test
    @DisplayName("C04: Test getCourseCode()")
    void getExamSchedules() {
        Course course = new Course("CS103");
        Date examDate = new Date();
        course.addExamSchedule(examDate);
        List<Date> examSchedules = course.getExamSchedules();
        assertNotNull(examSchedules);
        assertEquals(1, examSchedules.size());
        assertEquals(examDate, examSchedules.get(0));
    }

    @Test
    @DisplayName("C05: Test addAssignment()")
    void addAssignment() {
        Course course = new Course("CS103");
        Assignment assignment = new Assignment("Assignment 1", new Date());
        course.addAssignment(assignment);
        List<Assignment> assignments = course.getAssignments();

        assertNotNull(assignments);
        assertEquals(1, assignments.size());
        assertEquals("Assignment 1", assignments.get(0).getName());
        assertEquals(assignment.getDueDate(), assignments.get(0).getDueDate());
    }

    @Test
    @DisplayName("C06: Test addExamSchedule()")
    void addExamSchedule() {
        Course course = new Course("CS103");
        Date examDate1 = new Date();
        Date examDate2 = new Date(examDate1.getTime() + 1000000);

        List<Date> examSchedules = course.getExamSchedules();
        assertNotNull(examSchedules);
        assertTrue(examSchedules.isEmpty());

        // Add exam dates to the course
        course.addExamSchedule(examDate1);
        course.addExamSchedule(examDate2);

        examSchedules = course.getExamSchedules();
        assertNotNull(examSchedules);
        assertEquals(2, examSchedules.size());
        assertEquals(examDate1, examSchedules.get(0));
        assertEquals(examDate2, examSchedules.get(1));
    }
}