package controller;

import model.Student;
import model.Subject;
import repository.StudentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api") // Esto hace que todas las rutas empiecen con /api automáticamente
public class StudentController {

    private final StudentRepository studentRepo;

    // INYECCIÓN DE DEPENDENCIAS: Spring Boot nos entrega el repositorio listo para usar
    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("/status")
    public String checkStatus() {
        return "¡El Sistema de Gestión de Tutorías está online y listo para recibir peticiones!";
    }

    @GetMapping("/subjects")
    public List<Subject> getAvailableSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Algebra"));
        subjects.add(new Subject("Physics"));
        subjects.add(new Subject("Chemistry"));
        return subjects;
    }

    // NUEVO ROUTER POST MEJORADO CON REGLAS DE NEGOCIO
    @PostMapping("/students")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        
        // REGLA 1: Validar unicidad de identidad
        if (studentRepo.existsByEmail(student.getEmail())) {
            // Si el correo existe, devolvemos un error HTTP 409 (Conflicto)
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Error de Negocio: El correo '" + student.getEmail() + "' ya se encuentra registrado.");
        }

        // Si sobrevive a la validación, lo guardamos
        studentRepo.save(student);
        
        // Devolvemos el alumno con un éxito HTTP 201 (Creado)
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // NUEVO: Router para ver todos los alumnos guardados
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
