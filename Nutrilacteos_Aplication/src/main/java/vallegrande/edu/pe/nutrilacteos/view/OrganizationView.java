package vallegrande.edu.pe.nutrilacteos.view;

import java.sql.Timestamp;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vallegrande.edu.pe.nutrilacteos.model.Organization;

public class OrganizationView extends BorderPane {

    private final TableView<Organization> tabla = new TableView<>();
    private final Button btnToggleStatus = new Button("⚡  Activar / Inactivar (1-Clic)");
    private final Button btnRecargar = new Button("🔄  Recargar");
    private final Label lblMensaje = new Label();

    @SuppressWarnings("unchecked")
    public OrganizationView() {
        Label titulo = new Label("Gestión de Organizaciones");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #0F172A;");

        // Configuración de las columnas
        TableColumn<Organization, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idOrganization"));

        TableColumn<Organization, Integer> colTypeId = new TableColumn<>("ID Tipo Ident.");
        colTypeId.setCellValueFactory(new PropertyValueFactory<>("idTypeIdentification"));

        TableColumn<Organization, Integer> colUbigeo = new TableColumn<>("ID Ubigeo");
        colUbigeo.setCellValueFactory(new PropertyValueFactory<>("idUbigeo"));

        TableColumn<Organization, String> colName = new TableColumn<>("Organización");
        colName.setCellValueFactory(new PropertyValueFactory<>("nameOrganization"));

        TableColumn<Organization, String> colTaxId = new TableColumn<>("RUC / Doc");
        colTaxId.setCellValueFactory(new PropertyValueFactory<>("taxIdentification"));

        TableColumn<Organization, String> colPhone = new TableColumn<>("Teléfono");
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Organization, String> colAddress = new TableColumn<>("Dirección");
        colAddress.setCellValueFactory(new PropertyValueFactory<>("streetAddress"));

        TableColumn<Organization, String> colStatus = new TableColumn<>("Estado");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Formato de color para el estado (Verde = Active, Rojo = Inactive)
        colStatus.setCellFactory(col -> new TableCell<Organization, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if ("Active".equalsIgnoreCase(item)) {
                        setStyle("-fx-text-fill: #059669; -fx-font-weight: bold; -fx-alignment: CENTER;");
                    } else {
                        setStyle("-fx-text-fill: #DC2626; -fx-font-weight: bold; -fx-alignment: CENTER;");
                    }
                }
            }
        });

        TableColumn<Organization, Timestamp> colRegDate = new TableColumn<>("Fecha Registro");
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        tabla.getColumns().addAll(
                colId, colTypeId, colUbigeo, colName,
                colTaxId, colPhone, colAddress, colStatus, colRegDate
        );

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Estilos para los botones de acción
        btnToggleStatus.setStyle("-fx-background-color: #0F766E; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8 16; -fx-background-radius: 6; -fx-cursor: hand;");
        btnRecargar.setStyle("-fx-background-color: #64748B; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8 16; -fx-background-radius: 6; -fx-cursor: hand;");

        lblMensaje.setStyle("-fx-font-size: 13px; -fx-text-fill: #475569;");

        HBox acciones = new HBox(12, btnToggleStatus, btnRecargar);
        acciones.setPadding(new Insets(5, 0, 5, 0));

        VBox contenido = new VBox(15, titulo, tabla, acciones, lblMensaje);
        contenido.setPadding(new Insets(20));

        setCenter(contenido);
    }

    public TableView<Organization> getTabla() { return tabla; }
    public Button getBtnToggleStatus() { return btnToggleStatus; }
    public Button getBtnRecargar() { return btnRecargar; }
    public Label getLblMensaje() { return lblMensaje; }
}