package cc.davyy.studentmanagement.managers;

import cc.davyy.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class is responsible for managing students in a student management system.
 * It provides methods to add, view, and delete students.
 */
public class StudentManager {

    private final List<Student> students = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void addStudent(Student student) {
        if (students.stream().noneMatch(s -> s.id() == student.id())) {
            students.add(student);
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student with ID " + student.id() + " already exists.");
        }
    }

    public void viewStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("Student List:");
        students.forEach(System.out::println);
    }

    public void deleteStudent(int id) {
        Optional<Student> student = findStudentById(id);
        if (student.isPresent()) {
            students.remove(student.get());
            System.out.println("Student with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public List<Student> getAllStudents() { return new ArrayList<>(students); }

    private Optional<Student> findStudentById(int id) {
        return students
                .stream()
                .filter(student -> student.id() == id)
                .findFirst();
    }

}