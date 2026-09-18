package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Le decimos al radar de Spring Boot que SOLO escanee tus carpetas, no todo el sistema
@SpringBootApplication(scanBasePackages = {"app", "controller", "model", "repository", "util", "service"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("=== Servidor Web Iniciado ===");
    }
}
