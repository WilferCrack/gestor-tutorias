package repository;

import model.Student;
import java.util.List;
import java.util.ArrayList;

public class StudentRepository {
    private List<Student> database;

    public StudentRepository() {
        this.database = new ArrayList<>();
    }

    public void save(Student student) {
        database.add(student);
        System.out.println("[LOG DB] Alumno " + student.getName() + " guardado en la base de datos.");
    }

    public List<Student> findAll() {
        return database;
    }
}