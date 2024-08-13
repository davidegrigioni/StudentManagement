package cc.davyy.studentmanagement;

import cc.davyy.studentmanagement.gui.StudentGUI;

import javax.swing.*;

public class StudentManagement {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGUI gui = new StudentGUI();
            gui.setVisible(true);
        });
    }

}