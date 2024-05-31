import java.util.*;

// LMS class to handle main functionality
public class LMS {
    private List<User> users;
    private List<Course> courses;
    private final long TIMEOUT_DURATION = 10 * 60 * 1000; // 10 minutes timeout duration in milliseconds
    // Other properties and methods

    public LMS() {
        this.users = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Method to add a user to the system
    public void addUser(User user) {
        users.add(user);
    }

    // Method to add a course to the system
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Authenticate user based on username and password
    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Return null if authentication fails
    }

    public void accessCourseMaterial(User user, Course course) {
        if (user instanceof Student) {
            ((Student) user).viewCourseMaterial(course);
        } else if (user instanceof Staff) {
            // Implement staff access to course material
            System.out.println("Staff viewing course material for " + course.getCourseCode());
        } else {
            System.out.println("Invalid user type");
        }
        user.updateActivityTime(); // Update activity time upon accessing course material
    }

    // Method to generate and display academic calendar
    public void displayAcademicCalendar() {
        for (Course course : courses) {
            System.out.println("Course: " + course.getCourseCode());
            System.out.println("Assignments:");
            for (Assignment assignment : course.getAssignments()) {
                System.out.println(" - " + assignment.getName() + " Due Date: " + assignment.getDueDate());
            }
            System.out.println("Exam Schedules:");
            for (Date examDate : course.getExamSchedules()) {
                System.out.println(" - Exam Date: " + examDate);
            }
            System.out.println();
        }
    }

    // Method for students to upload assignment
    public void studentUploadAssignment(Student student, Course course, Assignment assignment) {
        student.uploadAssignment(course, assignment);
    }

    // Method for students to take online exam
    public void studentTakeOnlineExam(Student student, Course course) {
        student.takeOnlineExam(course);
    }

    public void studentCheckGrades(Student student) {
        student.checkGrades();
    }

    // Method for academics to upload content
    public void academicUploadContent(Staff staff, Course course, String content) {
        staff.uploadContent(course, content);
    }

    public void academicCommunicateWithStudents(Staff staff, Course course, String message) {
        staff.communicateWithStudents(course, message);
    }

    // Method to check for user inactivity and request authentication
    public void checkUserInactivity(User user) {
        long currentTime = new Date().getTime();
        long lastActivityTime = user.getLastActivityTime().getTime();
        if (currentTime - lastActivityTime > TIMEOUT_DURATION) {
            System.out.println("User inactive for more than 10 minutes. Please re-authenticate.");
            // Code to request re-authentication
        }
    }

    // Method to logout user
    public void logout(User user) {
        user.logout();
    }

}