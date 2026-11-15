package vallegrande.edu.pe.nutrilacteos.model;

import java.sql.Timestamp;

public class Usuario {

    private int idUser;
    private String fullName;
    private String username;
    private String password;
    private Timestamp registrationDate;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor para registro de nuevos usuarios
    public Usuario(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    // Constructor completo para recuperar de la BD
    public Usuario(int idUser, String fullName, String username, String password, Timestamp registrationDate) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    // Getters y Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}