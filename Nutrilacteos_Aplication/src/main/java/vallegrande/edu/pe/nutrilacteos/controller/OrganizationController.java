package vallegrande.edu.pe.nutrilacteos.controller;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import vallegrande.edu.pe.nutrilacteos.model.Organization;
import vallegrande.edu.pe.nutrilacteos.model.OrganizationDAO;
import vallegrande.edu.pe.nutrilacteos.view.OrganizationView;

public class OrganizationController {

    private final OrganizationView vista;
    private final OrganizationDAO dao;

    public OrganizationController(OrganizationView vista) {
        this.vista = vista;
        this.dao = new OrganizationDAO();

        vista.getBtnRecargar().setOnAction(e -> cargarOrganizaciones());
        vista.getBtnToggleStatus().setOnAction(e -> alternarEstadoDirecto());

        cargarOrganizaciones();
    }

    private void cargarOrganizaciones() {
        try {
            vista.getTabla().setItems(
                    FXCollections.observableArrayList(dao.listar())
            );
            vista.getLblMensaje().setText(
                    vista.getTabla().getItems().isEmpty()
                            ? "No hay organizaciones registradas."
                            : "Organizaciones cargadas correctamente desde MySQL."
            );
        } catch (SQLException e) {
            mostrarError("Error al cargar organizaciones: " + e.getMessage());
        }
    }

    // Cambia automáticamente de Active a Inactive o viceversa
    private void alternarEstadoDirecto() {
        Organization seleccionada = vista.getTabla().getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            vista.getLblMensaje().setText("⚠️ Debes seleccionar una organización de la tabla.");
            return;
        }

        String nuevoEstado = "Active".equalsIgnoreCase(seleccionada.getStatus()) ? "Inactive" : "Active";

        try {
            if (dao.actualizarEstado(seleccionada.getIdOrganization(), nuevoEstado)) {
                vista.getLblMensaje().setText("✅ Estado de '" + seleccionada.getNameOrganization() + "' cambiado a " + nuevoEstado + ".");
                cargarOrganizaciones();
            } else {
                vista.getLblMensaje().setText("❌ No se pudo actualizar el estado en la base de datos.");
            }
        } catch (SQLException | IllegalArgumentException e) {
            mostrarError("Error al actualizar estado: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error de Sistema");
        alerta.setHeaderText("Módulo Organizaciones");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}