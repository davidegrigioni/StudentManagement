package cc.davyy.studentmanagement.managers;

import cc.davyy.studentmanagement.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {

    private StudentManager studentManager;

    @BeforeEach
    void setUp() {
        studentManager = new StudentManager();
    }

    @Test
    void testAddValidStudent() {
        Student student = new Student(1, "john doe", 20);
        studentManager.addStudent(student);

        assertEquals(1, studentManager.getAllStudents().size());
        assertEquals("John Doe", studentManager.getAllStudents().getFirst().name());
    }

    @Test
    void testAddStudentWithInvalidName() {
        Student student = new Student(1, "john123", 20);
        studentManager.addStudent(student);

        assertTrue(studentManager.getAllStudents().isEmpty());
    }

    @Test
    void testAddStudentWithExistingId() {
        Student student1 = new Student(1, "john doe", 20);
        Student student2 = new Student(1, "jane doe", 22);

        studentManager.addStudent(student1);
        studentManager.addStudent(student2);

        assertEquals(1, studentManager.getAllStudents().size());
        assertEquals("John Doe", studentManager.getAllStudents().getFirst().name());
    }

}