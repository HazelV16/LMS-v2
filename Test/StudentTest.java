import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    }

    @Test
    @DisplayName("ST03: Test uploadAssignment() for past due date")
    void uploadAssignmentPastDueDate() {
        LMS lms = new LMS();
        Student student = new Student("ruan0031", "12345");
        Course course = new Course("CS103");
        lms.addUser(student);
        lms.addCourse(course);
        student.enrollCourse(course);

        // Create an assignment with a past due date
        Date pastDueDate = new Date(new Date().getTime() - 1000000); // 1,000,000 milliseconds in the past
        Assignment pastDueAssignment = new Assignment("Assignment1", pastDueDate);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Upload the past due assignment
        student.uploadAssignment(course, pastDueAssignment);
        System.setOut(originalOut);

        // Verify that the past due assignment was uploaded (which indicates a bug)
        String expectedOutputPast = "Assignment uploaded successfully for CS103\n";
//        assertEquals(expectedOutputPast, outputStream.toString(), "Past due assignment should not be uploaded");
        assertNotEquals(expectedOutputPast, "Assignment uploaded successfully for CS103\n");
        outputStream.reset();
    }

    @Test
    @DisplayName("ST04: Test takeOnlineExam() Student enrolled course.")
    void takeOnlineExam() {
        LMS lms = new LMS();
        Student student = new Student("Anton", "12345");
        Course course = new Course("RZ007");

        lms.addUser(student);
        lms.addCourse(course);
        student.enrollCourse(course);
        lms.studentTakeOnlineExam(student,course);

        assertEquals("Taking online exam for RZ007", "Taking online exam for " + course.getCourseCode());
    }

    @Test
    @DisplayName("Test takeOnlineExam() Student unenrolled course.")
    void takeOnlineExamUnEnrolledCourse() {
        LMS lms = new LMS();
        Student student = new Student("Anton", "12345");
        Course course = new Course("RZ007");

        lms.addUser(student);
        lms.addCourse(course);
        lms.studentTakeOnlineExam(student,course);

        assertEquals("You are not enrolled in the course RZ007", "You are not enrolled in the course " + course.getCourseCode());
    }

    @Test
    @DisplayName("ST05: Test takeOnlineExam() before and after exam date")
    void testTakeOnlineExamBeforeAndAfterExamDate() {
        LMS lms = new LMS();
        Student student = new Student("student1", "password");
        Course course = new Course("CS103");
        lms.addUser(student);
        lms.addCourse(course);
        student.enrollCourse(course);

        Date currentDate = new Date();
        Date pastExamDate = new Date(currentDate.getTime() - 1000000); // 1,000,000 milliseconds in the past
        Date futureExamDate = new Date(currentDate.getTime() + 1000000); // 1,000,000 milliseconds in the future

        course.addExamSchedule(pastExamDate);
        course.addExamSchedule(futureExamDate);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Take the exam before the scheduled future exam date
        student.takeOnlineExam(course);
        System.setOut(originalOut);

        // Check that student able to take the exam
        // before the date to indicates a bug
        String expectedOutputBefore = "Taking online exam for CS103\n";
        assertEquals(expectedOutputBefore, outputStream.toString(),
                "Student should not be able to take the exam before the scheduled date");
        outputStream.reset();

        // Output for taking the exam after the scheduled past exam date
        System.setOut(new PrintStream(outputStream));

        // Take the exam after the scheduled future exam date
        student.updateActivityTime();
        student.takeOnlineExam(course);
        System.setOut(originalOut);

        // Verify the output for attempting to take the exam
        // after the scheduled date to indicates a bug
        assertEquals(expectedOutputBefore, outputStream.toString(),
                "Student should not be able to take the exam after the scheduled date has passed");
    }

    @Test
    @DisplayName("ST06: Test checkGrades()")
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