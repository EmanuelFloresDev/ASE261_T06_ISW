package vallegrande.edu.pe.nutrilacteos.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vallegrande.edu.pe.nutrilacteos.model.Usuario;
import vallegrande.edu.pe.nutrilacteos.view.LoginView;
import vallegrande.edu.pe.nutrilacteos.view.MainView;
import vallegrande.edu.pe.nutrilacteos.view.OrganizationView;
import vallegrande.edu.pe.nutrilacteos.view.RegisterRequestView;
import vallegrande.edu.pe.nutrilacteos.view.RequestContactView;

public class MainController {

    private final MainView vista;
    private final Stage stage;
    private final Usuario usuario;

    public MainController(MainView vista, Stage stage, Usuario usuario) {
        this.vista = vista;
        this.stage = stage;
        this.usuario = usuario;

        this.vista.getBtnInicio().setOnAction(e -> mostrarInicio());
        this.vista.getBtnRegisterRequest().setOnAction(e -> mostrarRegistrarConsulta());
        this.vista.getBtnOrganizations().setOnAction(e -> mostrarOrganizaciones());
        this.vista.getBtnRequestContacts().setOnAction(e -> mostrarSolicitudes());
        this.vista.getBtnLogout().setOnAction(e -> cerrarSesion());

        mostrarInicio();
    }

    private void mostrarInicio() {
        vista.seleccionarBoton(vista.getBtnInicio());

        String nombre = (usuario != null) ? usuario.getFullName() : "";
        Label bienvenida = new Label("¡Bienvenido a NutriLácteos, " + nombre + "!");
        bienvenida.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #0F172A;");

        Label descripcion = new Label("Selecciona una opción del menú lateral para comenzar a gestionar.");
        descripcion.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B;");

        VBox contenido = new VBox(12, bienvenida, descripcion);
        contenido.setAlignment(Pos.CENTER);
        contenido.setPadding(new Insets(30));

        vista.setCenter(contenido);
    }

    private void mostrarRegistrarConsulta() {
        vista.seleccionarBoton(vista.getBtnRegisterRequest());
        RegisterRequestView regView = new RegisterRequestView();
        new RegisterRequestController(regView);
        vista.setCenter(regView);
    }

    private void mostrarOrganizaciones() {
        vista.seleccionarBoton(vista.getBtnOrganizations());
        OrganizationView orgView = new OrganizationView();
        new OrganizationController(orgView);
        vista.setCenter(orgView);
    }

    private void mostrarSolicitudes() {
        vista.seleccionarBoton(vista.getBtnRequestContacts());
        RequestContactView reqView = new RequestContactView();
        new RequestContactController(reqView);
        vista.setCenter(reqView);
    }

    private void cerrarSesion() {
        LoginView loginView = new LoginView();
        new LoginController(loginView, stage);

        Scene escenaLogin = new Scene(loginView, 900, 600);
        stage.setScene(escenaLogin);
        stage.setTitle("NutriLácteos - Autenticación");
        stage.centerOnScreen();
    }
}