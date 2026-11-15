package vallegrande.edu.pe.nutrilacteos.view;

import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vallegrande.edu.pe.nutrilacteos.model.RequestContact;

public class RequestContactView extends BorderPane {

    private final TableView<RequestContact> tabla = new TableView<>();
    private final ComboBox<String> cbStatus = new ComboBox<>();
    private final TextArea txtObservation = new TextArea();
    private final DatePicker dpDateAttention = new DatePicker();

    private final Button btnActualizar = new Button("Actualizar");
    private final Button btnEliminar = new Button("Eliminar");
    private final Button btnRecargar = new Button("Recargar");
    private final Label lblMensaje = new Label();

    @SuppressWarnings("unchecked")
    public RequestContactView() {
        Label titulo = new Label("Solicitudes de contacto");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Configuración de columnas vinculadas a las propiedades del modelo RequestContact
        TableColumn<RequestContact, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idRequest"));

        TableColumn<RequestContact, Integer> colResponsible = new TableColumn<>("ID Responsable");
        colResponsible.setCellValueFactory(new PropertyValueFactory<>("idResponsible"));

        TableColumn<RequestContact, String> colDescription = new TableColumn<>("Descripción");
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<RequestContact, String> colStatus = new TableColumn<>("Estado");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<RequestContact, String> colObservation = new TableColumn<>("Observación");
        colObservation.setCellValueFactory(new PropertyValueFactory<>("observation"));

        TableColumn<RequestContact, LocalDateTime> colDateRequest = new TableColumn<>("Fecha Solicitud");
        colDateRequest.setCellValueFactory(new PropertyValueFactory<>("dateRequest"));

        TableColumn<RequestContact, LocalDateTime> colDateAttention = new TableColumn<>("Fecha Atención");
        colDateAttention.setCellValueFactory(new PropertyValueFactory<>("dateAttention"));

        tabla.getColumns().addAll(
                colId, colResponsible, colDescription,
                colStatus, colObservation, colDateRequest, colDateAttention
        );

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Opciones del ComboBox alineadas con el CHECK constraint de la base de datos
        cbStatus.setItems(FXCollections.observableArrayList(
                "Pending", "In Progress", "Attended", "Cancelled"
        ));
        cbStatus.setPromptText("Estado");

        txtObservation.setPromptText("Observaciones sobre la solicitud...");
        txtObservation.setPrefRowCount(2);

        dpDateAttention.setPromptText("Fecha de atención (opcional)");

        HBox acciones = new HBox(
                10, cbStatus, dpDateAttention, btnActualizar, btnEliminar, btnRecargar
        );

        VBox contenido = new VBox(
                15, titulo, tabla, new Label("Observación:"),
                txtObservation, acciones, lblMensaje
        );
        contenido.setPadding(new Insets(20));

        setCenter(contenido);
    }

    public TableView<RequestContact> getTabla() {
        return tabla;
    }

    public ComboBox<String> getCbStatus() {
        return cbStatus;
    }

    public TextArea getTxtObservation() {
        return txtObservation;
    }

    public DatePicker getDpDateAttention() {
        return dpDateAttention;
    }

    public Button getBtnActualizar() {
        return btnActualizar;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public Button getBtnRecargar() {
        return btnRecargar;
    }

    public Label getLblMensaje() {
        return lblMensaje;
    }
}