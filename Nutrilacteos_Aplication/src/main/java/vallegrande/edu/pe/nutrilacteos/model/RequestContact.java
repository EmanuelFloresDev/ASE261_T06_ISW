package vallegrande.edu.pe.nutrilacteos.model;

import java.time.LocalDateTime;

public class RequestContact {

    private int idRequest;
    private int idResponsible;
    private String description;
    private LocalDateTime dateRequest;
    private String status;
    private String observation;
    private LocalDateTime dateAttention;

    public RequestContact() {
    }

    public RequestContact(int idResponsible, String description, String status) {
        this.idResponsible = idResponsible;
        this.description = description;
        this.status = status;
    }

    public RequestContact(int idRequest, int idResponsible, String description,
                          LocalDateTime dateRequest, String status,
                          String observation, LocalDateTime dateAttention) {
        this.idRequest = idRequest;
        this.idResponsible = idResponsible;
        this.description = description;
        this.dateRequest = dateRequest;
        this.status = status;
        this.observation = observation;
        this.dateAttention = dateAttention;
    }

    public int getIdRequest() { return idRequest; }
    public void setIdRequest(int idRequest) { this.idRequest = idRequest; }

    public int getId() { return idRequest; }
    public void setId(int id) { this.idRequest = id; }

    public int getIdResponsible() { return idResponsible; }
    public void setIdResponsible(int idResponsible) { this.idResponsible = idResponsible; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDateRequest() { return dateRequest; }
    public void setDateRequest(LocalDateTime dateRequest) { this.dateRequest = dateRequest; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public LocalDateTime getDateAttention() { return dateAttention; }
    public void setDateAttention(LocalDateTime dateAttention) { this.dateAttention = dateAttention; }
}