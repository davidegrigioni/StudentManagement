package cc.davyy.studentmanagement;

import cc.davyy.studentmanagement.managers.StudentManager;

import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> studentManager.addStudent();
                case 2 -> studentManager.viewStudent();
                case 3 -> studentManager.deleteStudent();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
    }

}