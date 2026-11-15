package vallegrande.edu.pe.nutrilacteos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RequestContactDAO {

    // Método por defecto: Ordena por id_request DESC y date_request DESC
    public List<RequestContact> listar() throws SQLException {
        return listarOrdenado("id_request DESC", "date_request DESC");
    }

    // Método para Ordenamiento Multi-Criterio seguro
    public List<RequestContact> listarOrdenado(String primerCriterio, String segundoCriterio) throws SQLException {
        List<RequestContact> solicitudes = new ArrayList<>();

        String sql = String.format("""
                SELECT id_request, id_responsible, description,
                       date_request, status, observation, date_attention
                FROM REQUEST_CONTACT
                ORDER BY %s, %s
                """, primerCriterio, segundoCriterio);

        Connection conexion = Conexion.conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Timestamp fechaSolicitud = rs.getTimestamp("date_request");
                Timestamp fechaAtencion = rs.getTimestamp("date_attention");

                solicitudes.add(new RequestContact(
                        rs.getInt("id_request"),
                        rs.getInt("id_responsible"),
                        rs.getString("description"),
                        fechaSolicitud != null ? fechaSolicitud.toLocalDateTime() : null,
                        rs.getString("status"),
                        rs.getString("observation"),
                        fechaAtencion != null ? fechaAtencion.toLocalDateTime() : null
                ));
            }
        }
        return solicitudes;
    }

    public boolean cambiarEstadoDirecto(int idRequest, String nuevoEstado) throws SQLException {
        validarEstado(nuevoEstado);
        String sql = "UPDATE REQUEST_CONTACT SET status = ? WHERE id_request = ?";

        Connection conexion = Conexion.conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idRequest);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean registrarConsultaCompleta(
            String nombreOrg, String rucOrg, String telefonoOrg, String direccionOrg,
            String nombreResp, String emailResp, String telefonoResp,
            String descripcion) throws SQLException {

        String sqlOrg = "INSERT INTO ORGANIZATION (id_type_identification, id_ubigeo, name_organization, tax_identification, phone, street_address) VALUES (1, 1, ?, ?, ?, ?)";
        String sqlResp = "INSERT INTO RESPONSIBLE (id_organization, name_responsible, email, number) VALUES (?, ?, ?, ?)";
        String sqlReq = "INSERT INTO REQUEST_CONTACT (id_responsible, description, status) VALUES (?, ?, 'Pending')";

        Connection con = null;
        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            int idOrg = 0;
            try (PreparedStatement ps = con.prepareStatement(sqlOrg, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nombreOrg);
                ps.setString(2, rucOrg);
                ps.setString(3, telefonoOrg);
                ps.setString(4, direccionOrg);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) idOrg = rs.getInt(1);
                }
            }

            int idResp = 0;
            try (PreparedStatement ps = con.prepareStatement(sqlResp, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, idOrg);
                ps.setString(2, nombreResp);
                ps.setString(3, emailResp);
                ps.setString(4, telefonoResp);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) idResp = rs.getInt(1);
                }
            }

            try (PreparedStatement ps = con.prepareStatement(sqlReq)) {
                ps.setInt(1, idResp);
                ps.setString(2, descripcion);
                ps.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    public boolean insertar(RequestContact solicitud) throws SQLException {
        validarEstado(solicitud.getStatus());
        String sql = """
                INSERT INTO REQUEST_CONTACT (id_responsible, description, status, observation, date_attention)
                VALUES (?, ?, ?, ?, ?)
                """;

        Connection conexion = Conexion.conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, solicitud.getIdResponsible());
            ps.setString(2, solicitud.getDescription());
            ps.setString(3, solicitud.getStatus() != null ? solicitud.getStatus() : "Pending");
            ps.setString(4, solicitud.getObservation());

            if (solicitud.getDateAttention() == null) {
                ps.setNull(5, java.sql.Types.TIMESTAMP);
            } else {
                ps.setTimestamp(5, Timestamp.valueOf(solicitud.getDateAttention()));
            }

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(RequestContact solicitud) throws SQLException {
        validarEstado(solicitud.getStatus());
        String sql = """
                UPDATE REQUEST_CONTACT
                SET status = ?, observation = ?, date_attention = ?
                WHERE id_request = ?
                """;

        Connection conexion = Conexion.conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, solicitud.getStatus());
            ps.setString(2, solicitud.getObservation());

            if (solicitud.getDateAttention() == null) {
                ps.setNull(3, java.sql.Types.TIMESTAMP);
            } else {
                ps.setTimestamp(3, Timestamp.valueOf(solicitud.getDateAttention()));
            }

            ps.setInt(4, solicitud.getIdRequest());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idRequest) throws SQLException {
        String sql = "DELETE FROM REQUEST_CONTACT WHERE id_request = ?";
        Connection conexion = Conexion.conectar();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idRequest);
            return ps.executeUpdate() > 0;
        }
    }

    private void validarEstado(String status) {
        if (status != null && !status.matches("Pending|In Progress|Attended|Cancelled")) {
            throw new IllegalArgumentException("Estado inválido. Debe ser: Pending, In Progress, Attended o Cancelled.");
        }
    }
}