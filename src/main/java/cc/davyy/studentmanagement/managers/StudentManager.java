package cc.davyy.studentmanagement.managers;

import cc.davyy.studentmanagement.model.Student;
import org.apache.commons.text.WordUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        if (!isValidName(student.name())) {
            System.out.println("Invalid name: " + student.name() + ". Names cannot contain numbers or special characters.");
            return;
        }

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

    public void exportStudentsToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header line
            writer.write(String.format("%-10s%-20s%-5s", "ID", "Name", "Age"));
            writer.newLine();
            writer.write("-------------------------------------------");
            writer.newLine();

            // Write each student's data in a tabular format
            for (Student student : students) {
                writer.write(String.format("%-10d%-20s%-5d", student.id(), student.name(), student.age()));
                writer.newLine();
            }

            System.out.println("Students exported successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while exporting students: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() { return new ArrayList<>(students); }

    private Optional<Student> findStudentById(int id) {
        return students.stream()
                .filter(student -> student.id() == id)
                .findFirst();
    }

    private boolean isValidName(String name) { return name != null && name.matches("[a-zA-Z\\s]+"); }

}