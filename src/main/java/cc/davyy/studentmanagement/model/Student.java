package cc.davyy.studentmanagement.model;

public record Student(int id, String name, int age) {

    @Override
    public String toString() { return "ID: " + id + ", Name: " + name + ", Age: " + age; }

}