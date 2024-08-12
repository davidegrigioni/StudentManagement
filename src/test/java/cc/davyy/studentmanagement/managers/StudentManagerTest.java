package cc.davyy.studentmanagement.managers;

import cc.davyy.studentmanagement.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {

    @Test
    void addStudent() {
        Student student = new Student(1, "John Doe", 20);

        assertEquals(1, student.id());
        assertEquals("John Doe", student.name());
        assertEquals(20, student.age());
    }

    @Test
    void testStudentToString() {
        Student student = new Student(1, "John Doe", 20);

        assertEquals("ID: 1, Name: John Doe, Age: 20", student.toString());
    }

}