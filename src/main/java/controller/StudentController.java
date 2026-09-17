package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    // Este es tu primer endpoint (router) HTTP GET
    @GetMapping("/api/status")
    public String checkStatus() {
        return "¡El Sistema de Gestión de Tutorías está online y listo para recibir peticiones!";
    }
}
