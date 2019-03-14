package main;

import java.util.Scanner;

public class Starter {

    private static IService service;

    public static void main(String[] args) {
        service = new ListService();
        boolean exit = false;
        setup();
        while(!exit) {
            printActions();
            switch(getInput()) {
                case 1:
                    printStudents();
                    break;
                case 2:
                    findStudentById();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }

    private static void setup() {
        service.save(new Student("Nils", "Benz"));
        service.save(new Student("Tilon", "Heimgartner"));
    }

    private static void printActions() {
        System.out.println("1 | Schüler anzeigen");
        System.out.println("2 | Schüler mit ID suchen");
        System.out.println("3 | Schüler hinzufügen");
        System.out.println("4 | Beenden");
    }

    private static int getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int output = 0;
        try {
            output = Integer.parseInt(input);
        } catch(NumberFormatException ignored) {}
        return output;
    }

    private static void printStudents() {
        for(Student s : service.findAll()) {
            System.out.print(s);
        }
    }

    private static void findStudentById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie die ID des gesuchten Schülers an: ");
        String input = scanner.nextLine();
        Student student = service.findById(input);
        System.out.print(student);
    }

    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie den Vornamen des gesuchten Schülers an: ");
        String firstName = scanner.nextLine();
        System.out.println("Geben Sie den Nachnamen des gesuchten Schülers an: ");
        String lastName = scanner.nextLine();
        service.save(new Student(firstName, lastName));
    }
}
