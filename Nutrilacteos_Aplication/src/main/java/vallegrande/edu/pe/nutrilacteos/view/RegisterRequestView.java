package vallegrande.edu.pe.nutrilacteos.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RegisterRequestView extends BorderPane {

    // Campos Organización
    private final TextField txtNombreOrg = new TextField();
    private final TextField txtRucOrg = new TextField();
    private final TextField txtTelefonoOrg = new TextField();
    private final TextField txtDireccionOrg = new TextField();

    // Campos Responsable
    private final TextField txtNombreResp = new TextField();
    private final TextField txtEmailResp = new TextField();
    private final TextField txtTelefonoResp = new TextField();

    // Campo Solicitud
    private final TextArea txtDescripcion = new TextArea();

    // Botones y Mensaje
    private final Button btnGuardar = new Button("💾   Registrar Consulta");
    private final Button btnLimpiar = new Button("🧹   Limpiar Campos");
    private final Label lblMensaje = new Label();

    public RegisterRequestView() {
        Label titulo = new Label("Registrar Nueva Consulta");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #0F172A;");

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(12);
        grid.setPadding(new Insets(10, 0, 10, 0));

        // Estilos de Entradas
        String estiloInput = "-fx-padding: 9; -fx-background-radius: 6; -fx-font-size: 13px;";
        txtNombreOrg.setStyle(estiloInput);
        txtRucOrg.setStyle(estiloInput);
        txtTelefonoOrg.setStyle(estiloInput);
        txtDireccionOrg.setStyle(estiloInput);
        txtNombreResp.setStyle(estiloInput);
        txtEmailResp.setStyle(estiloInput);
        txtTelefonoResp.setStyle(estiloInput);
        txtDescripcion.setStyle(estiloInput);
        txtDescripcion.setPrefRowCount(3);

        // Sección 1: Datos Organización
        Label lblSec1 = new Label("🏢 DATOS DE LA ORGANIZACIÓN");
        lblSec1.setStyle("-fx-font-weight: bold; -fx-text-fill: #0F766E;");
        grid.add(lblSec1, 0, 0, 2, 1);

        grid.add(new Label("Organización / Empresa:"), 0, 1);
        grid.add(txtNombreOrg, 1, 1);
        grid.add(new Label("RUC / Doc. Identidad:"), 0, 2);
        grid.add(txtRucOrg, 1, 2);
        grid.add(new Label("Teléfono Empresa:"), 0, 3);
        grid.add(txtTelefonoOrg, 1, 3);
        grid.add(new Label("Dirección:"), 0, 4);
        grid.add(txtDireccionOrg, 1, 4);

        // Sección 2: Datos Responsable
        Label lblSec2 = new Label("👤 DATOS DEL RESPONSABLE DE CONTACTO");
        lblSec2.setStyle("-fx-font-weight: bold; -fx-text-fill: #0F766E;");
        grid.add(lblSec2, 2, 0, 2, 1);

        grid.add(new Label("Nombre Completo:"), 2, 1);
        grid.add(txtNombreResp, 3, 1);
        grid.add(new Label("Correo Electrónico:"), 2, 2);
        grid.add(txtEmailResp, 3, 2);
        grid.add(new Label("Teléfono Personal:"), 2, 3);
        grid.add(txtTelefonoResp, 3, 3);

        // Sección 3: Descripción
        Label lblSec3 = new Label("✉ DETALLE DE LA CONSULTA");
        lblSec3.setStyle("-fx-font-weight: bold; -fx-text-fill: #0F766E;");
        grid.add(lblSec3, 0, 5, 4, 1);
        grid.add(txtDescripcion, 0, 6, 4, 1);

        btnGuardar.setStyle("-fx-background-color: #0F766E; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6; -fx-cursor: hand;");
        btnLimpiar.setStyle("-fx-background-color: #64748B; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6; -fx-cursor: hand;");

        VBox contenedorAcciones = new VBox(10, btnGuardar, btnLimpiar, lblMensaje);
        contenedorAcciones.setAlignment(Pos.CENTER_LEFT);

        VBox contenido = new VBox(15, titulo, grid, contenedorAcciones);
        contenido.setPadding(new Insets(20));

        setCenter(contenido);
    }

    public TextField getTxtNombreOrg() { return txtNombreOrg; }
    public TextField getTxtRucOrg() { return txtRucOrg; }
    public TextField getTxtTelefonoOrg() { return txtTelefonoOrg; }
    public TextField getTxtDireccionOrg() { return txtDireccionOrg; }
    public TextField getTxtNombreResp() { return txtNombreResp; }
    public TextField getTxtEmailResp() { return txtEmailResp; }
    public TextField getTxtTelefonoResp() { return txtTelefonoResp; }
    public TextArea getTxtDescripcion() { return txtDescripcion; }
    public Button getBtnGuardar() { return btnGuardar; }
    public Button getBtnLimpiar() { return btnLimpiar; }
    public Label getLblMensaje() { return lblMensaje; }
}