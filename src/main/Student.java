package main;

import java.util.UUID;

public class Student {

    private String id;
    private String firstName;
    private String lastName;

    Student(String firstname, String lastname) {
        this.firstName = firstname;
        this.lastName = lastname;
    }

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "----------------------\n" +
                "ID: " + id + "\n" +
                "Vorname: " + firstName + "\n" +
                "Nachname: " + lastName + "\n" +
                "----------------------\n\n";
    }
}
