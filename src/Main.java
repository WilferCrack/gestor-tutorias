import model.Subject;
import model.Student;
import model.TutoringSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main{
    public static void main(String[] arg){
        System.out.println("Starting Tutoring Management System...");

        //1. Instanciar (crear) los objetos a partir de nuestra clase
        Subject math = new Subject("Algebra");
        Subject physics = new Subject("Physics");
        Subject chemistry = new Subject("Chemistry");

        Student alumno = new Student("Alumno", "Apelido", "alumpellido@gmail.com");
        //Inscribir al alumno en las materias (¡El paso que faltaba!)
        alumno.enroll(math);
        alumno.enroll(physics);

        TutoringSession sesion1 = new TutoringSession(alumno, math, LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        // 2. Usar los métodos públicos (getters) para acceder a los datos
        System.out.println("Subject 1:" + math.getName());
        System.out.println("Subject 2:" + physics.getName());
        System.out.println("Subject 3:" + chemistry.getName());
        System.out.println("Student 1:" + alumno.getName() + " " + alumno.getLastName() + " " + alumno.getEmail());
        System.out.println("Reserva Confirmada: " + sesion1.getStudent().getName() + " tiene tutoria de " + math.getName() + " el dia " + sesion1.getSessionDate().format(formatter) );
    }
}
