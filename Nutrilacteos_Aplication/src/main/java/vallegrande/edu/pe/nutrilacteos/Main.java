package vallegrande.edu.pe.nutrilacteos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vallegrande.edu.pe.nutrilacteos.controller.LoginController;
import vallegrande.edu.pe.nutrilacteos.model.Conexion;
import vallegrande.edu.pe.nutrilacteos.model.DbConnection;
import vallegrande.edu.pe.nutrilacteos.view.ConnectionDialog;
import vallegrande.edu.pe.nutrilacteos.view.LoginView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main extends Application {

    private static DbConnection dbConnection;

    public static void main(String[] args) {
        dbConnection = ConnectionDialog.ask();

        if (dbConnection == null) {
            System.out.println("Conexión cancelada por el usuario.");
            System.exit(0);
        }

        Conexion.setConnection(dbConnection.get());

        try (PreparedStatement ps = Conexion.getConexion().prepareStatement("SELECT 1");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                System.out.println("Conexión OK, resultado: " + rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();

        new LoginController(loginView, primaryStage);

        Scene scene = new Scene(loginView, 800, 600);
        primaryStage.setTitle("NutriLácteos - Inicio de Sesión");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        if (dbConnection != null) {
            dbConnection.close();
            System.out.println("Túnel SSH y conexión a BD cerrados correctamente.");
        }
        super.stop();
        System.exit(0);
    }
}