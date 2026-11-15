package vallegrande.edu.pe.nutrilacteos.model;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {

    private static Connection connection;

    public static Connection getConexion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("No hay una conexión activa con la base de datos remota.");
        }
        return connection;
    }

    public static Connection conectar() throws SQLException {
        return getConexion();
    }

    public static Connection connection() throws SQLException {
        return getConexion();
    }

    public static void setConnection(Connection conn) {
        connection = conn;
    }
}