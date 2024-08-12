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

    public void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        if (findStudentById(id).isPresent()) {
            System.out.println("Error: A student with this ID already exists.");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();

        Student student = new Student(id, name, age);
        students.add(student);

        System.out.println("Student added successfully!");
    }

    public void viewStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("Student List:");
        students.forEach(System.out::println);
    }

    public void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        Student studentToRemove = null;
        for (Student student : students) {
            if (student.id() == id) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private Optional<Student> findStudentById(int id) {
        return students
                .stream()
                .filter(student -> student.id() == id)
                .findFirst();
    }

}