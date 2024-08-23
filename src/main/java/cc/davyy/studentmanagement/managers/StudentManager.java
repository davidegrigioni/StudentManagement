package cc.davyy.studentmanagement.managers;

import cc.davyy.studentmanagement.model.Student;
import org.apache.commons.text.WordUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for managing students in a student management system.
 * It provides methods to add, view, and delete students.
 */
public class StudentManager {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (students.stream()
                .anyMatch(s -> s.id() == student.id())) {
            System.out.println("Student with ID " + student.id() + " already exists.");
            return;
        }

        String capitalized = WordUtils.capitalize(student.name());
        Student capitalizeStudent = new Student(student.id(), capitalized, student.age());
        students.add(capitalizeStudent);
        System.out.println("Student added successfully.");
    }

    public void deleteStudent(int id) {
        Optional<Student> student = findStudentById(id);

        if (student.isEmpty()) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        students.remove(student.get());
        System.out.println("Student with ID " + id + " deleted successfully.");
    }

    public List<Student> getAllStudents() { return new ArrayList<>(students); }

    private Optional<Student> findStudentById(int id) {
        return students.stream()
                .filter(student -> student.id() == id)
                .findFirst();
    }

}