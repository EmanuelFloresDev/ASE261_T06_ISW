package vallegrande.edu.pe.nutrilacteos.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import vallegrande.edu.pe.nutrilacteos.model.RequestContact;
import vallegrande.edu.pe.nutrilacteos.model.RequestContactDAO;
import vallegrande.edu.pe.nutrilacteos.view.RequestContactView;

public class RequestContactController {

    private final RequestContactView vista;
    private final RequestContactDAO dao;

    public RequestContactController(RequestContactView vista) {
        this.vista = vista;
        this.dao = new RequestContactDAO();

        // Asignación de acciones a botones
        vista.getBtnRecargar().setOnAction(e -> cargarSolicitudes());
        vista.getBtnActualizar().setOnAction(e -> actualizarSolicitud());
        vista.getBtnEliminar().setOnAction(e -> eliminarSolicitud());

        // Listener para llenar los campos al seleccionar una fila de la tabla
        vista.getTabla().getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, seleccionada) -> {
                    if (seleccionada != null) {
                        vista.getCbStatus().setValue(seleccionada.getStatus());
                        vista.getTxtObservation().setText(
                                seleccionada.getObservation() == null ? "" : seleccionada.getObservation()
                        );

                        vista.getDpDateAttention().setValue(
                                seleccionada.getDateAttention() == null
                                        ? null
                                        : seleccionada.getDateAttention().toLocalDate()
                        );
                    } else {
                        limpiarFormulario();
                    }
                });

        // Carga inicial de datos
        cargarSolicitudes();
    }

    private void cargarSolicitudes() {
        try {
            vista.getTabla().setItems(
                    FXCollections.observableArrayList(dao.listar())
            );

            vista.getLblMensaje().setText(
                    vista.getTabla().getItems().isEmpty()
                            ? "No hay solicitudes de contacto registradas."
                            : "Solicitudes cargadas correctamente."
            );
        } catch (SQLException e) {
            mostrarError("Error al cargar solicitudes: " + e.getMessage());
        }
    }

    private void actualizarSolicitud() {
        RequestContact seleccionada = vista.getTabla().getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            vista.getLblMensaje().setText("Debes seleccionar una solicitud de la tabla.");
            return;
        }

        String estado = vista.getCbStatus().getValue();
        if (estado == null || estado.trim().isEmpty()) {
            vista.getLblMensaje().setText("Debes seleccionar un estado válido (Pending, In Progress, Attended, Cancelled).");
            return;
        }

        seleccionada.setStatus(estado);
        seleccionada.setObservation(
                vista.getTxtObservation().getText() == null || vista.getTxtObservation().getText().trim().isEmpty()
                        ? null : vista.getTxtObservation().getText().trim()
        );

        // Conversión de LocalDate a LocalDateTime (a inicio del día o medianoche)
        if (vista.getDpDateAttention().getValue() == null) {
            seleccionada.setDateAttention(null);
        } else {
            seleccionada.setDateAttention(
                    LocalDateTime.of(
                            vista.getDpDateAttention().getValue(),
                            LocalTime.MIDNIGHT
                    )
            );
        }

        try {
            if (dao.actualizar(seleccionada)) {
                vista.getLblMensaje().setText("Solicitud #" + seleccionada.getIdRequest() + " actualizada con éxito.");
                cargarSolicitudes();
            } else {
                vista.getLblMensaje().setText("No se pudo actualizar la solicitud.");
            }
        } catch (SQLException | IllegalArgumentException e) {
            mostrarError("Error al actualizar la solicitud: " + e.getMessage());
        }
    }

    private void eliminarSolicitud() {
        RequestContact seleccionada = vista.getTabla().getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            vista.getLblMensaje().setText("Debes seleccionar una solicitud para eliminar.");
            return;
        }

        // Confirmación de eliminación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("Eliminar Solicitud #" + seleccionada.getIdRequest());
        confirmacion.setContentText("¿Estás seguro de eliminar esta solicitud de contacto? Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            try {
                if (dao.eliminar(seleccionada.getIdRequest())) {
                    vista.getLblMensaje().setText("Solicitud #" + seleccionada.getIdRequest() + " eliminada.");
                    limpiarFormulario();
                    cargarSolicitudes();
                } else {
                    vista.getLblMensaje().setText("No se pudo eliminar la solicitud.");
                }
            } catch (SQLException e) {
                mostrarError("Error al eliminar la solicitud: " + e.getMessage());
            }
        }
    }

    private void limpiarFormulario() {
        vista.getCbStatus().setValue(null);
        vista.getTxtObservation().clear();
        vista.getDpDateAttention().setValue(null);
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error de Sistema");
        alerta.setHeaderText("Módulo Solicitudes de Contacto");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}