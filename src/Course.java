import java.util.*;

// Course class representing a course
class Course {
    private String courseCode;
    private List<Assignment> assignments;
    private List<Date> examSchedules;
    // Other properties

    public Course(String courseCode) {
        this.courseCode = courseCode;
        this.assignments = new ArrayList<>();
        this.examSchedules = new ArrayList<>();
    }

    // Methods to manage course content and assignments
    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<Date> getExamSchedules() {
        return examSchedules;
    }

    // Methods to manage course content and schedules
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void addExamSchedule(Date examDate) {
        examSchedules.add(examDate);
    }
}
