package main;

import java.util.ArrayList;
import java.util.List;

public class ListService implements IService {

    private List<Student> students;

    ListService() {
        this.students = new ArrayList<>();
    }

    @Override
    public void save(Student student) {
        student.generateId();
        this.students.add(student);
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Student findById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }
}
