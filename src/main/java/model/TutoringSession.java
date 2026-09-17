package model;

// Importar la herramienta moderna de java para manejar fechas y horas
import java.time.LocalDateTime;

public class TutoringSession {

    //Atributos
    private Student student;
    private Subject subject;
    private LocalDateTime sessionDate;

    //Constructor
    public TutoringSession(Student student, Subject subject, LocalDateTime sessionDate) {
        this.student = student;
        this.subject = subject;
        this.sessionDate = sessionDate;
    }

    //Getters
    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }
}