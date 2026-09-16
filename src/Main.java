import model.Subject;
import model.Student;
import java.util.Scanner;
import model.TutoringSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main{
    public static void main(String[] arg){
        // Inicializamos el lector del teclado
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting Tutoring Management System...");

        //1. Instanciar (crear) los objetos a partir de nuestra clase
        Subject math = new Subject("Algebra");
        Subject physics = new Subject("Physics");
        Subject chemistry = new Subject("Chemistry");

        System.out.println("--- Welcome to the Tutoring Management System ---");

        // Pido que ingrese datos al Usuario
        System.out.println("\n[Student Registration]");
        System.out.println("Enter your first name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        //Se crea el alumno con las variables que tipeo el usuario
        Student student = new Student(name, lastName, email);

        //Menu para elegir la materia
        System.out.println("\n[Available Sbjects]");
        System.out.println("1. " + math.getName());
        System.out.println("2. " + physics.getName());
        System.out.println("3. " + chemistry.getName());
        System.out.println("Select a subject (1-3): ");
        int choice = scanner.nextInt();

        //Logica de seleccion
        Subject selectedSubject = null;
        if (choice == 1) {
            selectedSubject = math;
        } else if (choice == 2) {
            selectedSubject = physics;
        } else if (choice == 3) {
            selectedSubject = chemistry;
        } else {
            System.out.println("Invalid choice. Defaul to Algebra");
            selectedSubject = math;
        }
            

        //Inscribir al student en las materias (¡El paso que faltaba!)
        student.enroll(selectedSubject);

        TutoringSession session = new TutoringSession(student, selectedSubject, LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        // 2. Usar los métodos públicos (getters) para acceder a los datos
        System.out.println("\n=== RESERVATION CONFIRMED ===");
        System.out.println("Student: " + session.getStudent().getName() + " " + session.getStudent().getLastName());
        System.out.println("Subject: " + session.getSubject().getName());
        System.out.println("Date: " + session.getSessionDate().format(formatter));
        System.out.println("=============================\n");

        scanner.close();
    }
}
