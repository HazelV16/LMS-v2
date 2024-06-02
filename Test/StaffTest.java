import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {

    @Test
    @DisplayName("SF01: Test uploadContent()")
    void uploadContent() {
        Staff staff = new Staff("Johnny", "myValentine");
        Course course = new Course("NCT127");
        staff.uploadContent(course, "Teaching Material");
        assertEquals("Johnny", staff.getUsername());
        assertEquals("NCT127", course.getCourseCode());
    }

    @Test
    @DisplayName("SF02: Test communicateWithStudents()")
    void communicateWithStudents() {
        Staff staff = new Staff("Jaehyun", "loveUSuhFam");
        Course course = new Course("NCT127");

        PrintStream originalContent = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        staff.communicateWithStudents(course, "2 baddies 1 porsche.");
        System.setOut(originalContent);

        String expectedOutput = "Communicating with students in course NCT127: 2 baddies 1 porsche.\n";
        assertEquals("Jaehyun", staff.getUsername());
        assertEquals("NCT127", course.getCourseCode());
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("SF03: Test updateActivityTime() staff")
    void testUpdateActivityTime() {
        Staff staff = new Staff("Seunghoon", "4444");
        Date initialTime = staff.getLastActivityTime();
        staff.updateActivityTime();
        assertEquals(initialTime, staff.getLastActivityTime());
    }

}