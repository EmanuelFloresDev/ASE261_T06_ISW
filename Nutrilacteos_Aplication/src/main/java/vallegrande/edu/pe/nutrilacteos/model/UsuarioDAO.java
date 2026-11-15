package vallegrande.edu.pe.nutrilacteos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Validar credenciales comparando la contraseña convertida a Hash
    public Usuario validar(String username, String passwordPlana) throws SQLException {
        String hashIngresado = HashUtil.hashPassword(passwordPlana);

        String sql = """
                SELECT id_user, full_name, username, password, registration_date
                FROM USERS
                WHERE username = ? AND password = ?
                """;

        Connection con = Conexion.conectar();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, hashIngresado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id_user"),
                            rs.getString("full_name"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getTimestamp("registration_date")
                    );
                }
            }
        }
        return null;
    }

    public boolean existeUsername(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USERS WHERE username = ?";

        Connection con = Conexion.conectar();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Insertar un nuevo usuario guardando la contraseña en formato Hash
    public boolean registrar(Usuario usuario) throws SQLException {
        String hashGuardar = HashUtil.hashPassword(usuario.getPassword());

        String sql = "INSERT INTO USERS (full_name, username, password) VALUES (?, ?, ?)";

        Connection con = Conexion.conectar();
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getFullName());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, hashGuardar);

            return ps.executeUpdate() > 0;
        }
    }
}