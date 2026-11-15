package vallegrande.edu.pe.nutrilacteos.controller;

import java.sql.SQLException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import vallegrande.edu.pe.nutrilacteos.model.Usuario;
import vallegrande.edu.pe.nutrilacteos.model.UsuarioDAO;
import vallegrande.edu.pe.nutrilacteos.view.LoginView;
import vallegrande.edu.pe.nutrilacteos.view.MainView;

public class LoginController {

    private final LoginView vista;
    private final UsuarioDAO dao;
    private final Stage stage;

    public LoginController(LoginView vista, Stage stage) {
        this.vista = vista;
        this.stage = stage;
        this.dao = new UsuarioDAO();

        vista.getBtnIngresar().setOnAction(e -> procesarLogin());
        vista.getBtnRegistrar().setOnAction(e -> procesarRegistro());
    }

    private void procesarLogin() {
        String user = vista.getTxtLoginUser().getText().trim();
        String pass = vista.getTxtLoginPass().getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Requeridos", "Por favor ingresa usuario y contraseña.");
            return;
        }

        try {
            Usuario usuario = dao.validar(user, pass);
            if (usuario != null) {
                abrirMenuPrincipal(usuario);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Acceso Denegado", "Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Conexión", "Error en la base de datos: " + e.getMessage());
        }
    }

    private void procesarRegistro() {
        String name = vista.getTxtRegName().getText().trim();
        String user = vista.getTxtRegUser().getText().trim();
        String pass = vista.getTxtRegPass().getText().trim();

        if (name.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Requeridos", "Todos los campos son obligatorios.");
            return;
        }

        try {
            if (dao.existeUsername(user)) {
                mostrarAlerta(Alert.AlertType.WARNING, "Usuario Existente", "El nombre de usuario '" + user + "' ya está registrado.");
                return;
            }

            Usuario nuevoUsuario = new Usuario(name, user, pass);
            if (dao.registrar(nuevoUsuario)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Registro Exitoso", "¡Cuenta creada! Ahora puedes iniciar sesión.");
                vista.getTxtRegName().clear();
                vista.getTxtRegUser().clear();
                vista.getTxtRegPass().clear();
                vista.mostrarLogin(); // Cambia suavemente a la pestaña de Iniciar Sesión
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el usuario.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Sistema", "Error al registrar: " + e.getMessage());
        }
    }

    private void abrirMenuPrincipal(Usuario usuario) {
        MainView mainView = new MainView(usuario);
        new MainController(mainView, stage, usuario);

        Scene scene = new Scene(mainView, 1100, 650);
        stage.setScene(scene);
        stage.setTitle("NutriLácteos - Sistema de Gestión");
        stage.centerOnScreen();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}