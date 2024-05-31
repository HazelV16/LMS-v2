import java.util.*;

// Student class inheriting from User
class Student extends User {
    private List<Course> coursesEnrolled;
    private Map<Course, Double> grades;
    private long lastActivityTime;

    public Student(String username, String password) {
        super(username, password);
        this.coursesEnrolled = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    // Methods for student actions
    public void viewCourseMaterial(Course course) {
        // Implement code to view course material
        System.out.println("Viewing course material for " + course.getCourseCode());
        updateActivityTime(); // Update activity time upon viewing course material
    }

    public void uploadAssignment(Course course, Assignment assignment) {
        // Check if student is enrolled in the course
        if (coursesEnrolled.contains(course)) {
            course.addAssignment(assignment);
            System.out.println("Assignment uploaded successfully for " + course.getCourseCode());
            updateActivityTime(); // Update activity time upon uploading assignment
        } else {
            System.out.println("You are not enrolled in the course " + course.getCourseCode());
        }
    }

    public void takeOnlineExam(Course course) {
        // Check if student is enrolled in the course
        if (coursesEnrolled.contains(course)) {
            // Implement code to take online exam
            System.out.println("Taking online exam for " + course.getCourseCode());
            updateActivityTime(); // Update activity time upon taking online exam
        } else {
            System.out.println("You are not enrolled in the course " + course.getCourseCode());
        }
    }

    public void checkGrades() {
        System.out.println("Your Grades:");
        for (Map.Entry<Course, Double> entry : grades.entrySet()) {
            System.out.println("Course: " + entry.getKey().getCourseCode() + ", Grade: " + entry.getValue());
        }
        updateActivityTime(); // Update activity time upon checking grades
    }

    // Other methods as needed

    // Method to enroll in a course
    public void enrollCourse(Course course) {
        coursesEnrolled.add(course);
    }

    // Method to set grade for a course
    public void setGrade(Course course, double grade) {
        grades.put(course, grade);
    }

    // Other methods as needed
}
