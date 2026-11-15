package vallegrande.edu.pe.nutrilacteos.controller;

import java.sql.SQLException;
import javafx.scene.control.Alert;
import vallegrande.edu.pe.nutrilacteos.model.RequestContactDAO;
import vallegrande.edu.pe.nutrilacteos.view.RegisterRequestView;

public class RegisterRequestController {

    private final RegisterRequestView vista;
    private final RequestContactDAO dao;

    public RegisterRequestController(RegisterRequestView vista) {
        this.vista = vista;
        this.dao = new RequestContactDAO();

        vista.getBtnGuardar().setOnAction(e -> procesarRegistro());
        vista.getBtnLimpiar().setOnAction(e -> limpiarFormulario());
    }

    private void procesarRegistro() {
        String nombreOrg = vista.getTxtNombreOrg().getText().trim();
        String rucOrg = vista.getTxtRucOrg().getText().trim();
        String telOrg = vista.getTxtTelefonoOrg().getText().trim();
        String dirOrg = vista.getTxtDireccionOrg().getText().trim();

        String nombreResp = vista.getTxtNombreResp().getText().trim();
        String emailResp = vista.getTxtEmailResp().getText().trim();
        String telResp = vista.getTxtTelefonoResp().getText().trim();

        String descripcion = vista.getTxtDescripcion().getText().trim();

        if (nombreOrg.isEmpty() || rucOrg.isEmpty() || nombreResp.isEmpty() || emailResp.isEmpty() || descripcion.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor completa los campos obligatorios de la organización, responsable y consulta.");
            return;
        }

        try {
            if (dao.registrarConsultaCompleta(nombreOrg, rucOrg, telOrg, dirOrg, nombreResp, emailResp, telResp, descripcion)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "¡Consulta y organización registradas correctamente en la Base de Datos!");
                limpiarFormulario();
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error en BD", "No se pudo registrar la consulta: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        vista.getTxtNombreOrg().clear();
        vista.getTxtRucOrg().clear();
        vista.getTxtTelefonoOrg().clear();
        vista.getTxtDireccionOrg().clear();
        vista.getTxtNombreResp().clear();
        vista.getTxtEmailResp().clear();
        vista.getTxtTelefonoResp().clear();
        vista.getTxtDescripcion().clear();
        vista.getLblMensaje().setText("");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}