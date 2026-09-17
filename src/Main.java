import model.Subject;
import model.Student;
import model.TutoringSession;
import repository.StudentRepository; //nueva capa de datos
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        
        // Encendemos nuestra "Base de datos" al arrancar el programa
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

                // VALIDACIÓN DE EMAIL
        String email = "";
        boolean isValidEmail = false;

        while (!isValidEmail) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();

            // Usamos el método .matches() de Java con un patrón Regex básico
            // "^.+@.+\\..+$" significa: Algo + @ + Algo + . + Algo
            if (email.matches("^.+@.+\\..+$")) {
                isValidEmail = true;
            } else {
                System.out.println("Error: Invalid email format. Please make sure it includes '@' and a domain (e.g., .com).\n");
            }
        }


        Student student = new Student(name, lastName, email);
        
        // GUARDAMOS EL ALUMNO OFICIALMENTE EN EL REPOSITORIO
        studentRepo.save(student);

        System.out.println("\n[Available Subjects]");
        System.out.println("1. " + math.getName());
        System.out.println("2. " + physics.getName());
        System.out.println("3. " + chemistry.getName());
        
        int choice = -1;
        boolean isValid = false;

        // Bucle de validación que construiste
        while (!isValid) {
            System.out.print("Select a subject (1-3): ");
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 3) {
                    isValid = true;
                } else {
                    System.out.println("Invalid number. Please select 1, 2, or 3.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You cannot enter letters. Please enter a number.\n");
            }
        }

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
