import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("ST01: Test uploadAssignment() student enrolled in the course.")
    void uploadAssignmentEnrolledCourse() {
        LMS lms = new LMS();
        Student student = new Student("Anton", "12345");
        Course course = new Course("RZ007");
        Assignment assignment = new Assignment("Impossible Report", new Date());

        student.enrollCourse(course);
        lms.addUser(student);
        lms.addCourse(course);
        lms.studentUploadAssignment(student, course, assignment);

        assertEquals(1, course.getAssignments().size());
        assertEquals("Impossible Report", assignment.getName());
        // Verify that the assignment exists in the list of assignments for the course
        assertTrue(course.getAssignments().contains(assignment));

    }

    @Test
    @DisplayName("ST02: Test uploadAssignment() student upload assignment to unenrolled course")
    void uploadAssignmentToUnenrolledCourse() {
        LMS lms = new LMS();
        Student student = new Student("Wonbin", "12345");
        Course course = new Course("RZ007");
        Assignment assignment = new Assignment("Impossible Report", new Date());

        lms.addUser(student);
        lms.addCourse(course);
        lms.studentUploadAssignment(student, course, assignment);

        assertEquals(0, course.getAssignments().size());
        assertEquals("You are not enrolled in the course RZ007", "You are not enrolled in the course " + course.getCourseCode());
        // Verify that the assignment exists in the list of assignments for the course
        assertFalse(course.getAssignments().contains(assignment));
    }

    @Test
    @DisplayName("ST03: Test uploadAssignment() for past due date")
    void uploadAssignmentPastDueDate() {
        Student student = new Student("student1", "password");
        Course course = new Course("CS101");
        student.enrollCourse(course);

        // Create an assignment with a due date in the past
        Assignment assignment = new Assignment("Assignment 1", new Date(System.currentTimeMillis() - 86400000)); // Due date 1 day ago
        course.addAssignment(assignment);

        // Attempt to upload the assignment
        // Use a lambda expression to call the method that might throw an exception
        // and assert that the expected exception is thrown
        assertThrows(RuntimeException.class, () -> student.uploadAssignment(course, assignment));
    }

    @Test
    @DisplayName("ST04: Test takeOnlineExam() Student enrolled course.")
    void takeOnlineExam() {
        // Create an instance of LMS, Student, and Course
        LMS lms = new LMS();
        Student student = new Student("Anton", "12345");
        Course course = new Course("RZ007");

        // Add the student to LMS and enroll the student in the course
        lms.addUser(student);
        student.enrollCourse(course);

        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to take an online exam
        student.takeOnlineExam(course);

        // Restore standard output
        System.setOut(System.out);

        // Verify the output message
        String expectedOutput = "Taking online exam for RZ007\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("ST05: Test takeOnlineExam() Student unenrolled course.")
    void takeOnlineExamUnEnrolledCourse() {
        // Create an instance of LMS, Student, and Course
        LMS lms = new LMS();
        Student student = new Student("Anton", "12345");
        Course course = new Course("RZ007");

        // Add the student to LMS
        lms.addUser(student);

        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to take an online exam for a course the student is not enrolled in
        student.takeOnlineExam(course);

        // Restore standard output
        System.setOut(System.out);

        // Verify the output message
        String expectedOutput = "You are not enrolled in the course RZ007\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    @DisplayName("ST06: Test takeOnlineExam() Student cannot take online exam after exam date")
    void takeOnlineExamAfterExamDate() {
        Course course = new Course("CS101");
        Date examDate = new Date(1643723400000L); // February 10, 2022 10:00 AM
        course.addExamSchedule(examDate);

        // Create a student enrolled in the course
        Student student = new Student("johnDoe", "password");
        student.enrollCourse(course);

        // Set the current date to before the exam date
        Date currentDate = new Date(examDate.getTime() + 3600000); // 1 hour before the exam date
        course.addExamSchedule(currentDate);
        student.takeOnlineExam(course);

        // Verify that the student cannot take the online exam
        assertThrows(RuntimeException.class, () -> student.takeOnlineExam(course));

    }

    @Test
    @DisplayName("ST07: Test takeOnlineExam() Student cannot take online exam before exam date")
    void testTakeOnlineExamBeforeExamDate() {

        Course course = new Course("CS101");
        Date examDate = new Date(1643723400000L); // February 10, 2022 10:00 AM
        course.addExamSchedule(examDate);

        // Create a student enrolled in the course
        Student student = new Student("johnDoe", "password");
        student.enrollCourse(course);

        // Set the current date to before the exam date
        Date currentDate = new Date(examDate.getTime() - 3600000); // 1 hour before the exam date
        course.addExamSchedule(currentDate);
        student.takeOnlineExam(course);

        // Verify that the student cannot take the online exam
        assertThrows(RuntimeException.class, () -> student.takeOnlineExam(course));

    }


    @Test
    @DisplayName("ST08: Test checkGrades()")
    void checkGrades() {
        Student student = new Student("ruan0031", "1234");
        Course course1 = new Course("CS102");
        Course course2 = new Course("CS103");
        student.enrollCourse(course1);
        student.enrollCourse(course2);
        student.setGrade(course1, 80.0);
        student.setGrade(course2, 90.0);

        Date initialActivityTime = student.getLastActivityTime();
        student.checkGrades();

        Date updatedActivityTime = student.getLastActivityTime();
        assertNotEquals(initialActivityTime, updatedActivityTime);
    }

}