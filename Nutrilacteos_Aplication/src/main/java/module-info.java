module vallegrande.edu.pe.nutrilacteos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;
    requires com.jcraft.jsch;

    exports vallegrande.edu.pe.nutrilacteos;
    exports vallegrande.edu.pe.nutrilacteos.model;
    exports vallegrande.edu.pe.nutrilacteos.view;
    exports vallegrande.edu.pe.nutrilacteos.controller;

    opens vallegrande.edu.pe.nutrilacteos to javafx.fxml;
    opens vallegrande.edu.pe.nutrilacteos.model to javafx.fxml;
    opens vallegrande.edu.pe.nutrilacteos.view to javafx.fxml;
    opens vallegrande.edu.pe.nutrilacteos.controller to javafx.fxml;
}
