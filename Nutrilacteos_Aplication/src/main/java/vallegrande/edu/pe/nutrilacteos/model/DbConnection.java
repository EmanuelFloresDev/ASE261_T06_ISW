package vallegrande.edu.pe.nutrilacteos.model;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Session session;
    private Connection connection;

    public DbConnection(String sshHost, int sshPort, String sshUser, String pemPath,
                        String dbEndpoint, int dbPort, String dbName, String dbUser, String dbPassword) throws Exception {

        // 1. Establecer el túnel SSH hacia la instancia EC2
        JSch jsch = new JSch();
        if (pemPath != null && !pemPath.trim().isEmpty()) {
            jsch.addIdentity(pemPath);
        }

        session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        // 2. Redireccionar un puerto local libre (0) hacia la base de datos RDS
        int localPort = session.setPortForwardingL(0, dbEndpoint, dbPort);

        // 3. Conectar JDBC usando localhost y el puerto redireccionado
        String url = "jdbc:mysql://localhost:" + localPort + "/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        connection = DriverManager.getConnection(url, dbUser, dbPassword);
    }

    public Connection get() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}