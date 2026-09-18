package repository;

import model.TutoringSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SessionRepository {
    
    private List<TutoringSession> database = new ArrayList<>();

    public void save(TutoringSession session) {
        database.add(session);
        System.out.println("[LOG DB] Reserva creada para: " + session.getStudent().getName() + " en " + session.getSubject().getName());
    }

    // LÓGICA DURA 1: Contar cuántos alumnos hay en una materia específica
    public int countStudentsInSubject(String subjectName) {
        int count = 0;
        for (TutoringSession session : database) {
            if (session.getSubject().getName().equalsIgnoreCase(subjectName)) {
                count++;
            }
        }
        return count;
    }

    // LÓGICA DURA 2: Saber si un alumno ya está inscrito en esa materia
    public boolean isStudentAlreadyEnrolled(String email, String subjectName) {
        for (TutoringSession session : database) {
            if (session.getStudent().getEmail().equalsIgnoreCase(email) && 
                session.getSubject().getName().equalsIgnoreCase(subjectName)) {
                return true; // Ya está inscrito
            }
        }
        return false; // No está inscrito
    }
    
    public List<TutoringSession> findAll() {
        return database;
    }
}
