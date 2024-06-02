import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class LMSTest {

    @Test
    @DisplayName("Test authenticateSuccess()")
    void authenticateSuccess() {
        LMS lms = new LMS();
        lms.addUser(new User("ruan0031", "12345"));
        User authenticatedUser = lms.authenticate("ruan0031", "12345");
        assertNotNull(authenticatedUser);
        assertEquals("ruan0031", authenticatedUser.getUsername(), authenticatedUser.getPassword());
    }

    @Test
    @DisplayName("Test authenticateSuccess() Failure")
    void authenticateFailure() {
        LMS lms = new LMS();
        lms.addUser(new User("ruan0031", "12345"));
        User authenticatedUser = lms.authenticate("invalidUser", "invalidPassword");
        assertNull(authenticatedUser);
    }

    @Test
    @DisplayName("Test accessCourseMaterial() Student can access course material")
    void studentAccessCourseMaterial() {
        LMS lms = new LMS();
        Student student = new Student("ruan0031", "12345");
        Course course = new Course("CS103");

        lms.addUser(student);
        lms.addCourse(course);
        lms.accessCourseMaterial(student, course);
        assertEquals("ruan0031", student.getUsername());
        assertNotNull(student.getLastActivityTime());
    }

    @Test
    @DisplayName("Test accessCourseMaterial() Staff can access course material")
    void staffAccessCourseMaterial() {
        LMS lms = new LMS();
        Staff staff = new Staff("Hazel", "10987");
        Course course = new Course("CSE101");

        lms.addUser(staff);
        lms.addCourse(course);
        lms.accessCourseMaterial(staff, course);
        assertEquals("Hazel", staff.getUsername());
        assertEquals("Staff viewing course material for CSE101", "Staff viewing course material for CSE101");
    }

    @Test
    @DisplayName("Test displayAcademicCalendar()")
    void displayAcademicCalendar() {
        LMS lms = new LMS();
        Course course1 = new Course("CSE101");
        Course course2 = new Course("CSE102");

        Assignment assignment1 = new Assignment("Report A", new Date(2024, 6, 12));
        Assignment assignment2 = new Assignment("Report B", new Date(2024, 6, 16));
        course1.addAssignment(assignment1);
        course1.addAssignment(assignment2);

        Date examDate1 = new Date(2024,6, 21);
        course2.addExamSchedule(examDate1);

        lms.addCourse(course1);
        lms.addCourse(course2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        lms.displayAcademicCalendar();


        String expectedOutput = "Course: CSE101\n" +
                "Assignments:\n" +
                " - " + assignment1.getName() + " Due Date: " + assignment1.getDueDate() +
                "\n - " + assignment2.getName() + " Due Date: " + assignment2.getDueDate() +
                "\nExam Schedules:\n" +
                "\nCourse: CSE102\n" +
                "Assignments:\n" +
                "Exam Schedules:\n" +
                " - Exam Date: " + examDate1 +
                "\n"+"\n";

        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }

    @Test
    @DisplayName("Test studentCheckGrades()")
    void studentCheckGrades() {
        LMS lms = new LMS();
        Student student = new Student("ruan0031", "1234");
        Course course = new Course("CS101");
        lms.addUser(student);
        student.enrollCourse(course);

        Assignment assignment = new Assignment("Assignment 1", new Date());
        double grade = 85.0;
        student.uploadAssignment(course, assignment);
        student.setGrade(course, grade);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        lms.studentCheckGrades(student);
        System.setOut(originalOut);

        // Verify the output
        String expectedOutput = "Your Grades:\n" +
                "Course: CS101, Grade: 85.0\n";
        assertEquals(expectedOutput, outputStream.toString());


    }

    @Test
    @DisplayName("Test academicUploadContent()")
    void academicUploadContent() {
        LMS lms = new LMS();
        Staff staff = new Staff("Seunghoon", "4444");
        Course course = new Course("HOLO4");
        String content = "Hello, Student!";

        lms.addUser(staff);
        lms.addCourse(course);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        lms.academicUploadContent(staff, course, content);
        String expectedOutput = "Content uploaded to course HOLO4: Hello, Student!\n";
        assertEquals(expectedOutput,outContent.toString());
        System.setOut(System.out);

    }

    @Test
    @DisplayName("Test academicCommunicateWithStudents()")
    void academicCommunicateWithStudents() {
        LMS lms = new LMS();
        Staff staff = new Staff("Seunghoon", "4444");
        Course course = new Course("HOLO4");
        lms.addUser(staff);
        lms.addCourse(course);

        PrintStream originalMSG = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        lms.academicCommunicateWithStudents(staff, course, "Everybody Jump!");
        System.setOut(originalMSG);

        String expectedOutput = "Communicating with students in course HOLO4: Everybody Jump!\n";
        assertEquals("Seunghoon", staff.getUsername());
        assertEquals("HOLO4", course.getCourseCode());
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Test checkUserInactivity() more than 10 mins")
    void checkUserInactivity() {
        LMS lms = new LMS();
        Student student = new Student("ruan0031", "12345");
        Staff staff = new Staff("Seunghoon", "4444");
        student.updateActivityTime();
        staff.updateActivityTime();

        // Wait for less than 15 minutes
        long currentTime = new Date().getTime();
        long simulatedTime = currentTime + (15 * 60 * 1000);
        student.updateActivityTime();
        staff.updateActivityTime();

        lms.checkUserInactivity(student);
        lms.checkUserInactivity(staff);

        assertTrue((simulatedTime - student.getLastActivityTime().getTime()) > 10 * 60 * 1000);

    }

    @Test
    @DisplayName("Test checkUserInactivity() less than 10 mins")
    void checkUserActiveLessThan10Mins() {

        LMS lms = new LMS();
        Student student = new Student("ruan0031", "12345");
        Staff staff = new Staff("Seunghoon", "4444");
        student.updateActivityTime();
        staff.updateActivityTime();

        // Wait for less than 10 minutes (5 minutes later)
        long currentTime = new Date().getTime();
        long simulatedTime = currentTime + (5 * 60 * 1000);
        student.updateActivityTime();
        staff.updateActivityTime();

        lms.checkUserInactivity(student);
        lms.checkUserInactivity(staff);

        assertTrue((simulatedTime - student.getLastActivityTime().getTime()) < 10 * 60 * 1000);

    }

    @Test
    @DisplayName("Test logout()")
    void logout() {
        LMS lms = new LMS();
        User user = new User("ruan0031", "12345");
        lms.addUser(user);
        User authenticatedUser = lms.authenticate(user.getUsername(), user.getPassword());

        assertNotNull(authenticatedUser);
        lms.logout(user);
        assertNull(lms.authenticate(user.getUsername(), user.getPassword()));
    }

}