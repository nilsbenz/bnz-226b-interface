package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileService implements IService {

    private final String filepath = "students.txt";

    FileService() {
        createFileIfNotPresent();
        clearFile();
    }

    @Override
    public void save(Student student) {
        ArrayList<Student> students = findAll();
        student.generateId();
        students.add(student);
        try (FileWriter jsonOut = new FileWriter(filepath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            jsonOut.write(gson.toJson(students));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Student> findAll() {
        StringBuilder stringBuilder = new StringBuilder();
        Gson gson = new Gson();
        createFileIfNotPresent();
        try (FileReader jsonIn = new FileReader(filepath)) {
            int input;
            while ((input = jsonIn.read()) != -1) {
                stringBuilder.append((char) input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String input = stringBuilder.toString();
        if (input.equals("")) {
            input = "[]";
        }
        Student[] allNotes = gson.fromJson(input, Student[].class);
        return new ArrayList<>(Arrays.asList(allNotes));
    }

    @Override
    public Student findById(String id) {
        return findAll().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    private void clearFile() {
        try (FileWriter jsonOut = new FileWriter(filepath)) {
            jsonOut.write("[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFileIfNotPresent() {
        if (!new File(filepath).exists()) {
            try (FileWriter jsonOut = new FileWriter(filepath)) {
                jsonOut.write("[]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
