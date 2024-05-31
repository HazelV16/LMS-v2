// Staff class inheriting from User
class Staff extends User {
    public Staff(String username, String password) {
        super(username, password);
    }

    // Methods for staff actions
    public void uploadContent(Course course, String content) {
        // Implement code to upload content to a course
        System.out.println("Content uploaded to course " + course.getCourseCode() + ": " + content);
        updateActivityTime(); // Update activity time upon uploading content
    }

    public void communicateWithStudents(Course course, String message) {
        // Implement code to communicate with students in a course
        System.out.println("Communicating with students in course " + course.getCourseCode() + ": " + message);
        updateActivityTime(); // Update activity time upon communicating with students
    }

    // Other methods as needed
}
