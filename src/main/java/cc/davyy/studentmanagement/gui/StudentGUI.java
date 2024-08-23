package cc.davyy.studentmanagement.gui;

import cc.davyy.studentmanagement.managers.StudentManager;
import cc.davyy.studentmanagement.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGUI extends JFrame {

    private final StudentManager studentManager;
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField ageField;
    private final JTextArea displayArea;

    public StudentGUI() {
        studentManager = new StudentManager();

        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idField = new JTextField(5);
        nameField = new JTextField(15);
        ageField = new JTextField(5);

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton deleteButton = new JButton("Delete Student");

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(viewButton, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        addButton.addActionListener(new AddStudentListener());
        viewButton.addActionListener(new ViewStudentsListener());
        deleteButton.addActionListener(new DeleteStudentListener());

        add(mainPanel);
    }

    // Add Student Button Action
    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());

                Student student = new Student(id, name, age);

                studentManager.addStudent(student);
                displayArea.setText("Student added successfully!");

                // Clear input fields
                idField.setText("");
                nameField.setText("");
                ageField.setText("");

            } catch (NumberFormatException ex) {
                displayArea.setText("Please enter valid data.");
            }
        }
    }

    // View Students Button Action
    private class ViewStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder studentList = new StringBuilder();

            studentManager.getAllStudents().forEach(student -> studentList.append(student.toString()).append("\n"));

            displayArea.setText(studentList.toString());
        }
    }

    // Delete Student Button Action
    private class DeleteStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(idField.getText());
                studentManager.deleteStudent(id);
                displayArea.setText("Student with ID " + id + " deleted.");
            } catch (NumberFormatException ex) {
                displayArea.setText("Please enter a valid ID.");
            }
        }
    }

}