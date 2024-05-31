import java.util.Date;

/**
 * NO NEED TO TEST THE MAIN CLASS
 */

public class main {
    public static void main(String[] args) {
        // Create an instance of the Learning Management System
        LMS lms = new LMS();

        // Create some users (students and staff)
        Student student1 = new Student("student1", "password1");
        Staff staff1 = new Staff("staff1", "password2");

        // Add users to the LMS
        lms.addUser(student1);
        lms.addUser(staff1);

        // Create some courses
        Course course1 = new Course("CS101");
        Course course2 = new Course("CS102");

        // Add courses to the LMS
        lms.addCourse(course1);
        lms.addCourse(course2);

        // Enroll student in courses
        student1.enrollCourse(course1);
        student1.enrollCourse(course2);

        // Authenticate users
        User authenticatedUser = lms.authenticate("student1", "password1");
        if (authenticatedUser != null) {
            System.out.println("Authentication successful for user: " + authenticatedUser.getUsername());
        } else {
            System.out.println("Authentication failed. Invalid username or password.");
        }

        // Access course material
        if (authenticatedUser instanceof Student) {
            lms.accessCourseMaterial((Student) authenticatedUser, course1);
        } else if (authenticatedUser instanceof Staff) {
            lms.accessCourseMaterial((Staff) authenticatedUser, course1);
        }

    }
}
