package dto;

public class ReservationRequest {
    private String email;
    private String subjectName;

    // Constructor vacío obligatorio para que Spring lea el JSON
    public ReservationRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
