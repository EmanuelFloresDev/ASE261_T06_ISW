package vallegrande.edu.pe.nutrilacteos.model;

import java.sql.Timestamp;

public class Organization {

    private int idOrganization;
    private int idTypeIdentification;
    private int idUbigeo;
    private String nameOrganization;
    private String taxIdentification;
    private String phone;
    private String streetAddress;
    private String status;
    private Timestamp registrationDate;

    // Constructor vacío
    public Organization() {
    }

    // Constructor completo con todos los campos de la BD
    public Organization(int idOrganization, int idTypeIdentification, int idUbigeo,
                        String nameOrganization, String taxIdentification, String phone,
                        String streetAddress, String status, Timestamp registrationDate) {
        this.idOrganization = idOrganization;
        this.idTypeIdentification = idTypeIdentification;
        this.idUbigeo = idUbigeo;
        this.nameOrganization = nameOrganization;
        this.taxIdentification = taxIdentification;
        this.phone = phone;
        this.streetAddress = streetAddress;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    // Getters y Setters
    public int getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
    }

    public int getIdTypeIdentification() {
        return idTypeIdentification;
    }

    public void setIdTypeIdentification(int idTypeIdentification) {
        this.idTypeIdentification = idTypeIdentification;
    }

    public int getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(int idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public String getTaxIdentification() {
        return taxIdentification;
    }

    public void setTaxIdentification(String taxIdentification) {
        this.taxIdentification = taxIdentification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}