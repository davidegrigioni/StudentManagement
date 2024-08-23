package cc.davyy.studentmanagement.gui;

import cc.davyy.studentmanagement.managers.StudentManager;
import cc.davyy.studentmanagement.model.Student;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up input fields with borders
        idField = createTextField(5);
        nameField = createTextField(15);
        ageField = createTextField(5);

        JButton addButton = createButton("Add Student", Color.GREEN);
        JButton viewButton = createButton("View Students", Color.BLUE);
        JButton deleteButton = createButton("Delete Student", Color.RED);
        JButton exportButton = createButton("Export Student", Color.CYAN);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        displayArea.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Set up the layout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addToGrid(inputPanel, new JLabel("ID:"), gbc, 0, 0);
        addToGrid(inputPanel, idField, gbc, 1, 0);

        addToGrid(inputPanel, new JLabel("Name:"), gbc, 0, 1);
        addToGrid(inputPanel, nameField, gbc, 1, 1);

        addToGrid(inputPanel, new JLabel("Age:"), gbc, 0, 2);
        addToGrid(inputPanel, ageField, gbc, 1, 2);

        gbc.gridwidth = 2;
        addToGrid(inputPanel, addButton, gbc, 0, 3);
        addToGrid(inputPanel, deleteButton, gbc, 0, 4);
        addToGrid(inputPanel, viewButton, gbc, 0, 5);
        addToGrid(inputPanel, exportButton, gbc, 0, 6);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new AddStudentListener());
        viewButton.addActionListener(new ViewStudentsListener());
        deleteButton.addActionListener(new DeleteStudentListener());
        exportButton.addActionListener(new ExportStudentsListener());

        add(mainPanel);
    }

    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return button;
    }

    private void addToGrid(JPanel panel, Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
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

    private class ExportStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentManager.getAllStudents().isEmpty()) {
                displayArea.setText("No students to export.");
                return;
            }

            JFileChooser fileChooser = new JFileChooser();

            // Add file filters for common formats
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV Files (*.csv)", "csv"));
            fileChooser.setAcceptAllFileFilterUsed(true);

            int option = fileChooser.showSaveDialog(StudentGUI.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                // Get selected file filter extension
                String fileExtension = "";
                FileNameExtensionFilter filter = (FileNameExtensionFilter) fileChooser.getFileFilter();
                if (filter.getDescription().contains("*.txt")) {
                    fileExtension = ".txt";
                } else if (filter.getDescription().contains("*.csv")) {
                    fileExtension = ".csv";
                }

                // Append the correct extension if it's not already in the file path
                if (!filePath.toLowerCase().endsWith(fileExtension)) {
                    filePath += fileExtension;
                }

                studentManager.exportStudentsToFile(filePath);
                displayArea.setText("Students exported to " + filePath);
            }
        }
    }

}