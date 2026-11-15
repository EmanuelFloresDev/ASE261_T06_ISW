package vallegrande.edu.pe.nutrilacteos.view;

import vallegrande.edu.pe.nutrilacteos.model.DbConnection;

import javax.swing.*;
import java.awt.*;

public class ConnectionDialog {

    public static DbConnection ask() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField txtSshHost = new JTextField();
        JTextField txtSshUser = new JTextField("ec2-user");
        JTextField txtPemPath = new JTextField();
        JButton btnBrowsePem = new JButton("Buscar .pem...");

        JPanel pemPanel = new JPanel(new BorderLayout());
        pemPanel.add(txtPemPath, BorderLayout.CENTER);
        pemPanel.add(btnBrowsePem, BorderLayout.EAST);

        btnBrowsePem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                txtPemPath.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        JTextField txtDbEndpoint = new JTextField();
        JTextField txtDbPort = new JTextField("3306");
        JTextField txtDbName = new JTextField();
        JTextField txtDbUser = new JTextField();
        JPasswordField txtDbPass = new JPasswordField();

        panel.add(new JLabel("--- SERVIDOR INTERMEDIO (EC2) ---"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("IP Pública EC2:"));
        panel.add(txtSshHost);
        panel.add(new JLabel("Usuario SSH:"));
        panel.add(txtSshUser);
        panel.add(new JLabel("Llave SSH (.pem):"));
        panel.add(pemPanel);

        panel.add(new JLabel("--- BASE DE DATOS PRIVADA (RDS) ---"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Endpoint RDS:"));
        panel.add(txtDbEndpoint);
        panel.add(new JLabel("Puerto BD:"));
        panel.add(txtDbPort);
        panel.add(new JLabel("Nombre BD:"));
        panel.add(txtDbName);
        panel.add(new JLabel("Usuario BD:"));
        panel.add(txtDbUser);
        panel.add(new JLabel("Contraseña BD:"));
        panel.add(txtDbPass);

        int option = JOptionPane.showConfirmDialog(null, panel, "Configuración de Conexión Remota",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String sshHost = txtSshHost.getText().trim();
                String sshUser = txtSshUser.getText().trim();
                String pemPath = txtPemPath.getText().trim();
                String dbEndpoint = txtDbEndpoint.getText().trim();
                int dbPort = Integer.parseInt(txtDbPort.getText().trim());
                String dbName = txtDbName.getText().trim();
                String dbUser = txtDbUser.getText().trim();
                String dbPass = new String(txtDbPass.getPassword());

                return new DbConnection(sshHost, 22, sshUser, pemPath, dbEndpoint, dbPort, dbName, dbUser, dbPass);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al establecer la conexión: " + e.getMessage(),
                        "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return ask();
            }
        }
        return null;
    }
}