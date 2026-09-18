package controller;

import dto.ReservationRequest;
import model.Student;
import model.Subject;
import model.TutoringSession;
import repository.StudentRepository;
import service.SessionService; // NUEVA IMPORTACIÓN
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository studentRepo;
    private final SessionService sessionService; // Nuestro nuevo gerente

    public StudentController(StudentRepository studentRepo, SessionService sessionService) {
        this.studentRepo = studentRepo;
        this.sessionService = sessionService;
    }

    @GetMapping("/status")
    public String checkStatus() {
        return "¡El Sistema de Gestión está online!";
    }

    @GetMapping("/subjects")
    public List<Subject> getAvailableSubjects() {
        // El controlador ya no crea la lista, se la pide al servicio
        return sessionService.getSubjectCatalog();
    }

    @PostMapping("/students")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        if (studentRepo.existsByEmail(student.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: El correo '" + student.getEmail() + "' ya existe.");
        }
        studentRepo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // EL ENDPOINT DE RESERVAS REFACTORIZADO
    @PostMapping("/sessions")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        try {
            // El controlador solo delega la tarea al servicio
            TutoringSession newSession = sessionService.processReservation(request.getEmail(), request.getSubjectName());
            return ResponseEntity.status(HttpStatus.CREATED).body(newSession);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
