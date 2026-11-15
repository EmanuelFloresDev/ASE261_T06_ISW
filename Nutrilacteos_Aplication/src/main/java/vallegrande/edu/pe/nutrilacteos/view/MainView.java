package vallegrande.edu.pe.nutrilacteos.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import vallegrande.edu.pe.nutrilacteos.model.Usuario;

public class MainView extends BorderPane {

    private final Button btnInicio = new Button("⌂   Inicio");
    private final Button btnRegisterRequest = new Button("📝   Registrar Consulta");
    private final Button btnOrganizations = new Button("▣   Organizaciones");
    private final Button btnRequestContacts = new Button("✉   Solicitudes de contacto");
    private final Button btnLogout = new Button("🚪   Cerrar Sesión");

    private Button botonActivo;

    private final String estiloBoton =
            "-fx-background-color: transparent; -fx-text-fill: #E2E8F0; -fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-padding: 13 15 13 15; -fx-background-radius: 8; -fx-cursor: hand;";
    private final String estiloHover =
            "-fx-background-color: #1E293B; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-padding: 13 15 13 15; -fx-background-radius: 8; -fx-cursor: hand;";
    private final String estiloActivo =
            "-fx-background-color: #0F766E; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-padding: 13 15 13 15; -fx-background-radius: 8; -fx-cursor: hand;";
    private final String estiloLogout =
            "-fx-background-color: #991B1B; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: CENTER; -fx-padding: 10 15 10 15; -fx-background-radius: 8; -fx-cursor: hand;";

    public MainView(Usuario usuario) {
        setStyle("-fx-background-color: #F1F5F9;");

        Label logo = new Label("🥛 NutriLácteos");
        logo.setStyle("-fx-text-fill: white; -fx-font-size: 21px; -fx-font-weight: bold;");

        String nombreMostrar = (usuario != null) ? usuario.getFullName() : "Usuario";
        Label lblBienvenida = new Label("Bienvenido, " + nombreMostrar);
        lblBienvenida.setStyle("-fx-text-fill: #38BDF8; -fx-font-size: 13px; -fx-font-weight: bold;");

        VBox encabezado = new VBox(6, logo, lblBienvenida);
        encabezado.setPadding(new Insets(25, 18, 20, 18));

        configurarEfectoHover(btnInicio);
        configurarEfectoHover(btnRegisterRequest);
        configurarEfectoHover(btnOrganizations);
        configurarEfectoHover(btnRequestContacts);

        btnLogout.setStyle(estiloLogout);
        btnLogout.setMaxWidth(Double.MAX_VALUE);

        VBox menu = new VBox(12, btnInicio, btnRegisterRequest, btnOrganizations, btnRequestContacts);
        menu.setPadding(new Insets(10, 14, 10, 14));
        menu.setFillWidth(true);

        VBox espacio = new VBox();
        VBox.setVgrow(espacio, Priority.ALWAYS);

        VBox contenedorInferior = new VBox(10, btnLogout);
        contenedorInferior.setPadding(new Insets(10, 14, 20, 14));

        VBox barraLateral = new VBox(10);
        barraLateral.setPrefWidth(255);
        barraLateral.setMinWidth(255);
        barraLateral.setStyle("-fx-background-color: #172554;");

        barraLateral.getChildren().addAll(encabezado, menu, espacio, contenedorInferior);
        setLeft(barraLateral);

        seleccionarBoton(btnInicio);
    }

    private void configurarEfectoHover(Button boton) {
        boton.setMaxWidth(Double.MAX_VALUE);
        boton.setStyle(estiloBoton);
        boton.setOnMouseEntered(e -> { if (boton != botonActivo) boton.setStyle(estiloHover); });
        boton.setOnMouseExited(e -> { if (boton != botonActivo) boton.setStyle(estiloBoton); });
    }

    public void seleccionarBoton(Button seleccionado) {
        this.botonActivo = seleccionado;
        btnInicio.setStyle(estiloBoton);
        btnRegisterRequest.setStyle(estiloBoton);
        btnOrganizations.setStyle(estiloBoton);
        btnRequestContacts.setStyle(estiloBoton);
        seleccionado.setStyle(estiloActivo);
    }

    public Button getBtnInicio() { return btnInicio; }
    public Button getBtnRegisterRequest() { return btnRegisterRequest; }
    public Button getBtnOrganizations() { return btnOrganizations; }
    public Button getBtnRequestContacts() { return btnRequestContacts; }
    public Button getBtnLogout() { return btnLogout; }
}