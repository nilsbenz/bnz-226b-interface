package main;

import java.util.List;

public interface IService {

    void save(Student student);
    List<Student> findAll();
    Student findById(String id);
}
