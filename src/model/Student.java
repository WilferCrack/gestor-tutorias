package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    //Atributes
    private String name;
    private String lastName;
    private String email;

    // Nueva asociación: Un estudiante tiene una LISTA de materias
    private List<Subject> enrolledSubjects;
    
    //Constructor
    public Student(String name, String lastName, String email){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        // Inicializamos la lista vacía para que no dé error al intentar agregar algo
        this.enrolledSubjects = new ArrayList<>(); 
    }
    
    //Getter
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    // Método para inscribir al alumno en una materia
    public void enroll(Subject subject){
        enrolledSubjects.add(subject);
        System.out.println(this.name + " se ha inscrito exitosamente en " + subject.getName());
    }
    
}
