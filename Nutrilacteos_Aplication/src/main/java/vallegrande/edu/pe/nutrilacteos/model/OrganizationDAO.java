package vallegrande.edu.pe.nutrilacteos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAO {

    // Método para obtener todas las organizaciones con todas sus columnas
    public List<Organization> listar() throws SQLException {
        List<Organization> organizaciones = new ArrayList<>();

        String sql = """
                SELECT id_organization, id_type_identification, id_ubigeo,
                       name_organization, tax_identification, phone,
                       street_address, status, registration_date
                FROM ORGANIZATION
                ORDER BY id_organization
                """;

        Connection conexion = Conexion.connection();
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Organization org = new Organization(
                        rs.getInt("id_organization"),
                        rs.getInt("id_type_identification"),
                        rs.getInt("id_ubigeo"),
                        rs.getString("name_organization"),
                        rs.getString("tax_identification"),
                        rs.getString("phone"),
                        rs.getString("street_address"),
                        rs.getString("status"),
                        rs.getTimestamp("registration_date")
                );
                organizaciones.add(org);
            }
        }

        return organizaciones;
    }

    // Método para actualizar únicamente el campo status (Active / Inactive)
    public boolean actualizarEstado(int idOrganization, String status)
            throws SQLException {

        if (!status.equals("Active") && !status.equals("Inactive")) {
            throw new IllegalArgumentException("Estado no válido. Debe ser Active o Inactive.");
        }

        String sql = """
                UPDATE ORGANIZATION
                SET status = ?
                WHERE id_organization = ?
                """;

        Connection conexion = Conexion.connection();
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, idOrganization);

            return ps.executeUpdate() > 0;
        }
    }
}