package cc.davyy.studentmanagement.model;

/**
 * @param id
 * @param name
 * @param age
 */
public record Student(int id, String name, int age) {

    @Override
    public String toString() { return "ID: " + id + ", Name: " + name + ", Age: " + age; }

}