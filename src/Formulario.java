import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Formulario extends JFrame {
    private JLabel userLabel, passwordLabel, hostLabel, portaLabel, databaseLabel, fileLabel;
    private JTextField userField, passwordField, hostField, portaField, databaseField, fileField;
    private JButton fileButton, processarButton;
    private JProgressBar progressBar;
    String confini = "config.ini";

    public Formulario() {
        setTitle("Exportação Usuarios | marcio28costa@hotmail.com | v.1");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));

        ConfigReader configReader = new ConfigReader();
        File configFile = new File(confini);
        Config config = null;
        try {
            if (configFile.exists()) {
                config = configReader.readConfig(configFile.getAbsolutePath());
            } else {
                configFile.createNewFile();
                 config = new Config("localhost", "3306", "mysql", "grants.sql" );
                configReader.writeConfig(configFile.getAbsolutePath(), config);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        userLabel = new JLabel("Usuário:");
        userField = new JTextField(10);
        panel.add(userLabel);
        panel.add(userField);

        passwordLabel = new JLabel("Senha:");
        passwordField = new JTextField(10);
        panel.add(passwordLabel);
        panel.add(passwordField);

        hostLabel = new JLabel("Host:");
        hostField = new JTextField(10);
        hostField.setText(config.getIp());
        panel.add(hostLabel);
        panel.add(hostField);

        portaLabel = new JLabel("Porta:");
        portaField = new JTextField(10);
        portaField.setText(config.getPort());
        panel.add(portaLabel);
        panel.add(portaField);

        databaseLabel = new JLabel("Database:");
        databaseField = new JTextField(10);
        databaseField.setText(config.getDatabase());
        panel.add(databaseLabel);
        panel.add(databaseField);

        fileLabel = new JLabel("Nome do Arquivo:");
        fileField = new JTextField(10);
        fileField.setText(config.getFilename());
        fileButton = new JButton("Abrir Arquivo");
        fileButton.setEnabled(false);
        panel.add(fileLabel);
        JPanel filePanel = new JPanel(new BorderLayout());
        filePanel.add(fileField, BorderLayout.CENTER);
        filePanel.add(fileButton, BorderLayout.EAST);
        panel.add(filePanel);

        processarButton = new JButton("Processar");
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        panel.add(processarButton);
        panel.add(progressBar);

        add(panel, BorderLayout.CENTER);

        processarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = passwordField.getText();
                String host = hostField.getText();
                String porta = portaField.getText();
                String database = databaseField.getText();
                String filename = fileField.getText();

                progressBar.setValue(0);
                progressBar.setMaximum(100);
                progressBar.setString("Processando...");

                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + database, user, password);
                            for (int i = 0; i <= 100; i++) {
                                Thread.sleep(50);
                                progressBar.setValue(i);
                            }
                            JOptionPane.showMessageDialog(Formulario.this, "Conexão bem-sucedida!");
                            connection.close();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(Formulario.this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        progressBar.setString("Concluído");
                    }
                };

                worker.execute();
            }
        });

        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(Formulario.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    fileField.setText(chooser.getSelectedFile().getName());
                }
            }
        });

        processarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = passwordField.getText();
                String host = hostField.getText();
                String porta = portaField.getText();
                String database = databaseField.getText();
                String filename = fileField.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + database, user, password);
                    JOptionPane.showMessageDialog(Formulario.this, "Conexão bem-sucedida!");
                    connection.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Formulario.this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                }
            }
        });
    }
 //public static void main(String[] args) {
//        Formulario form = new Formulario();
//        form.setVisible(true);
//    }
}
