package vallegrande.edu.pe.nutrilacteos.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginView extends StackPane {

    // Botones del Selector Superior
    private final Button btnTabLogin = new Button("Iniciar Sesión");
    private final Button btnTabRegister = new Button("Registrarse");

    // Formulario Login
    private final TextField txtLoginUser = new TextField();
    private final PasswordField txtLoginPass = new PasswordField();
    private final Button btnIngresar = new Button("Iniciar Sesión");

    // Formulario Registro
    private final TextField txtRegName = new TextField();
    private final TextField txtRegUser = new TextField();
    private final PasswordField txtRegPass = new PasswordField();
    private final Button btnRegistrar = new Button("Registrarse");

    private final VBox formLogin;
    private final VBox formRegister;

    // Estilos de botones del selector
    private final String estiloTabActivo =
            "-fx-background-color: #0F766E; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-radius: 8; -fx-cursor: hand;";
    private final String estiloTabInactivo =
            "-fx-background-color: transparent; -fx-text-fill: #94A3B8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-radius: 8; -fx-cursor: hand;";

    public LoginView() {
        setStyle("-fx-background-color: #0F172A;");

        // Contenedor principal (Tarjeta centrada y compacta)
        VBox card = new VBox(20);
        card.setMaxWidth(400);
        card.setMinWidth(400);

        // RESTRICCIÓN CLAVE: Evita que la tarjeta se estire verticalmente
        card.setMaxHeight(Region.USE_PREF_SIZE);

        card.setStyle("-fx-background-color: #1E293B; -fx-background-radius: 16; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 15, 0, 0, 8);");
        card.setPadding(new Insets(25));

        // Selector Superior Simétrico (50% / 50%)
        HBox selectorBar = new HBox(5);
        selectorBar.setStyle("-fx-background-color: #0F172A; -fx-padding: 4; -fx-background-radius: 10;");

        btnTabLogin.setMaxWidth(Double.MAX_VALUE);
        btnTabRegister.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(btnTabLogin, Priority.ALWAYS);
        HBox.setHgrow(btnTabRegister, Priority.ALWAYS);

        selectorBar.getChildren().addAll(btnTabLogin, btnTabRegister);

        // Formularios
        formLogin = crearFormularioLogin();
        formRegister = crearFormularioRegistro();

        StackPane contenedorFormularios = new StackPane(formLogin, formRegister);

        card.getChildren().addAll(selectorBar, contenedorFormularios);

        // Centrado exacto en pantalla
        getChildren().add(card);
        StackPane.setAlignment(card, Pos.CENTER);

        // Eventos para alternar entre Login y Registro
        btnTabLogin.setOnAction(e -> mostrarLogin());
        btnTabRegister.setOnAction(e -> mostrarRegistro());

        mostrarLogin();
    }

    public void mostrarLogin() {
        btnTabLogin.setStyle(estiloTabActivo);
        btnTabRegister.setStyle(estiloTabInactivo);
        formLogin.setVisible(true);
        formRegister.setVisible(false);
    }

    public void mostrarRegistro() {
        btnTabLogin.setStyle(estiloTabInactivo);
        btnTabRegister.setStyle(estiloTabActivo);
        formLogin.setVisible(false);
        formRegister.setVisible(true);
    }

    private VBox crearFormularioLogin() {
        Label lblTitulo = new Label("🥛 NutriLácteos");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label lblSubtitulo = new Label("Ingresa tus credenciales para acceder");
        lblSubtitulo.setStyle("-fx-font-size: 12px; -fx-text-fill: #94A3B8;");

        txtLoginUser.setPromptText("Nombre de usuario");
        txtLoginUser.setStyle("-fx-padding: 11; -fx-background-radius: 8; -fx-font-size: 13px;");

        txtLoginPass.setPromptText("Contraseña");
        txtLoginPass.setStyle("-fx-padding: 11; -fx-background-radius: 8; -fx-font-size: 13px;");

        btnIngresar.setMaxWidth(Double.MAX_VALUE);
        btnIngresar.setStyle(
                "-fx-background-color: #0F766E; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12; -fx-background-radius: 8; -fx-cursor: hand;"
        );

        VBox form = new VBox(15, lblTitulo, lblSubtitulo, txtLoginUser, txtLoginPass, btnIngresar);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(10, 5, 5, 5));
        return form;
    }

    private VBox crearFormularioRegistro() {
        Label lblTitulo = new Label("Crear Cuenta");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label lblSubtitulo = new Label("Regístrate para obtener acceso al sistema");
        lblSubtitulo.setStyle("-fx-font-size: 12px; -fx-text-fill: #94A3B8;");

        txtRegName.setPromptText("Nombre completo (ej. Emanuel)");
        txtRegName.setStyle("-fx-padding: 11; -fx-background-radius: 8; -fx-font-size: 13px;");

        txtRegUser.setPromptText("Usuario (único)");
        txtRegUser.setStyle("-fx-padding: 11; -fx-background-radius: 8; -fx-font-size: 13px;");

        txtRegPass.setPromptText("Contraseña");
        txtRegPass.setStyle("-fx-padding: 11; -fx-background-radius: 8; -fx-font-size: 13px;");

        btnRegistrar.setMaxWidth(Double.MAX_VALUE);
        btnRegistrar.setStyle(
                "-fx-background-color: #0f766e; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12; -fx-background-radius: 8; -fx-cursor: hand;"
        );

        VBox form = new VBox(12, lblTitulo, lblSubtitulo, txtRegName, txtRegUser, txtRegPass, btnRegistrar);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(5, 5, 5, 5));
        return form;
    }

    public TextField getTxtLoginUser() { return txtLoginUser; }
    public PasswordField getTxtLoginPass() { return txtLoginPass; }
    public Button getBtnIngresar() { return btnIngresar; }

    public TextField getTxtRegName() { return txtRegName; }
    public TextField getTxtRegUser() { return txtRegUser; }
    public PasswordField getTxtRegPass() { return txtRegPass; }
    public Button getBtnRegistrar() { return btnRegistrar; }
}