package repository;

import model.Student;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
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

    // NUEVA REGLA: Buscar si el email ya existe en la lista
    public boolean existsByEmail(String email) {
        for (Student s : database) {
            // equalsIgnoreCase compara ignorando si hay mayúsculas o minúsculas
            if (s.getEmail().equalsIgnoreCase(email)) {
                return true; 
            }
        }
        return false;
    }

    // NUEVO: Buscar y devolver el objeto Student usando su correo
    public Student findByEmail(String email) {
        for (Student s : database) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return s; // Lo encontramos, lo devolvemos
            }
        }
        return null; // No existe
    }


}