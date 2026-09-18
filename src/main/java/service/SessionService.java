package service;

import model.Student;
import model.Subject;
import model.TutoringSession;
import repository.SessionRepository;
import repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service // Le indicamos a Spring Boot que este es el "cerebro" de las reservas
public class SessionService {

    private final SessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    public SessionService(SessionRepository sessionRepo, StudentRepository studentRepo) {
        this.sessionRepo = sessionRepo;
        this.studentRepo = studentRepo;
    }

    // Catálogo interno simulado (luego vendrá de la base de datos)
    public List<Subject> getSubjectCatalog() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Algebra"));
        subjects.add(new Subject("Physics"));
        subjects.add(new Subject("Chemistry"));
        return subjects;
    }

    public TutoringSession processReservation(String email, String subjectName) {
        Student student = studentRepo.findByEmail(email);
        if (student == null) {
            throw new IllegalArgumentException("Error: Estudiante no encontrado.");
        }

        Subject selectedSubject = null;
        for (Subject sub : getSubjectCatalog()) {
            if (sub.getName().equalsIgnoreCase(subjectName)) {
                selectedSubject = sub;
                break;
            }
        }
        if (selectedSubject == null) {
            throw new IllegalArgumentException("Error: Materia no encontrada.");
        }

        if (sessionRepo.isStudentAlreadyEnrolled(email, selectedSubject.getName())) {
            throw new IllegalStateException("Error: El estudiante ya tiene una reserva activa para " + selectedSubject.getName());
        }

        if (sessionRepo.countStudentsInSubject(selectedSubject.getName()) >= 5) {
            throw new IllegalStateException("Error: Cupo lleno. La materia " + selectedSubject.getName() + " ya tiene 5 estudiantes.");
        }

        TutoringSession newSession = new TutoringSession(student, selectedSubject, LocalDateTime.now());
        sessionRepo.save(newSession);
        return newSession;
    }
}
