import model.Subject;
import model.Student;
import model.TutoringSession;
import repository.StudentRepository;
import util.Validator; // <-- Importamos nuestra nueva herramienta
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        StudentRepository studentRepo = new StudentRepository();

        Subject math = new Subject("Algebra");
        Subject physics = new Subject("Physics");
        Subject chemistry = new Subject("Chemistry");

        System.out.println("--- Welcome to the Tutoring Management System ---");

        System.out.println("\n[Student Registration]");
        System.out.print("Enter your first name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // 1. Usamos el validador con una sola línea de código
        String email = Validator.readValidEmail(scanner, "Enter your email: ");

        Student student = new Student(name, lastName, email);
        studentRepo.save(student);

        System.out.println("\n[Available Subjects]");
        System.out.println("1. " + math.getName());
        System.out.println("2. " + physics.getName());
        System.out.println("3. " + chemistry.getName());
        
        // 2. Usamos el validador para el menú con una sola línea de código
        int choice = Validator.readValidInt(scanner, "Select a subject (1-3): ", 1, 3);

        Subject selectedSubject = null;
        if (choice == 1) {
            selectedSubject = math;
        } else if (choice == 2) {
            selectedSubject = physics;
        } else if (choice == 3) {
            selectedSubject = chemistry;
        }

        student.enroll(selectedSubject);
        TutoringSession session = new TutoringSession(student, selectedSubject, LocalDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("\n=== RESERVATION CONFIRMED ===");
        System.out.println("Student: " + session.getStudent().getName() + " " + session.getStudent().getLastName());
        System.out.println("Subject: " + session.getSubject().getName());
        System.out.println("Date: " + session.getSessionDate().format(formatter));
        System.out.println("=============================\n");

        scanner.close();
    }
}
