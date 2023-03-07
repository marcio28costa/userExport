import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.TimeZone;

import static java.lang.Float.parseFloat;

public class Formulario extends JFrame {
    private JLabel userLabel, passwordLabel, hostLabel, portaLabel, databaseLabel, fileLabel, processLabel;
    private JTextField userField, passwordField, hostField, portaField, databaseField, fileField;
    private JButton fileButton, processarButton;
    // private JProgressBar progressBar;
    String confini = "config.ini";
    private Statement stmt = null;
    private ResultSet rs = null;
    private Date dataHoraAtual = new Date();

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
                config = new Config("localhost", "3306", "mysql", "grants.sql");
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
        passwordField = new JPasswordField();
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

        processLabel = new JLabel(" ");
        panel.add(processLabel);


        processarButton = new JButton("Processar");
        panel.add(processarButton);

        // progressBar = new JProgressBar();
        // progressBar.setStringPainted(true);
        // panel.add(progressBar);

        add(panel, BorderLayout.CENTER);

        processarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = passwordField.getText();
                String host = hostField.getText();
                String porta = portaField.getText();
                String database = databaseField.getText();
                String filename = fileField.getText();
/*
                progressBar.setValue(0);
                progressBar.setString("Processando...");

                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {

                            for (int i = 0; i <= 100; i++) {
                                Thread.sleep(50);
                                progressBar.setValue(i);
                            }
                        } catch (Exception ex) {
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        progressBar.setString("Concluído");
                    }
                };

                worker.execute();
*/
            }
        });

        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = fileField.getText();
                String filePath = System.getProperty("user.dir") + "/" + fileName;
                File file = new File(filePath);
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "Arquivo não existe: " + file, "Erro", JOptionPane.ERROR_MESSAGE);

                } else {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        processarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // progressBar.setValue(0);
                // progressBar.setString("Processando...");
                String user = userField.getText();
                String password = new String(passwordField.getText());
                String host = hostField.getText();
                String porta = portaField.getText();
                String database = databaseField.getText();
                String filename = fileField.getText();
                String versao = null;
                Integer total = 0;
                String url = "jdbc:mysql://" + host + ":" + porta + "/" + database + "?useLegacyDatetimeCode=false";
                try {
                    TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
                    Properties props = new Properties();
                    props.setProperty("user", user);
                    props.setProperty("password", password);
                    props.setProperty("serverTimezone", timeZone.getID());
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url, props);
                    // JOptionPane.showMessageDialog(Formulario.this, "Conexão bem-sucedida!");

                    File arquivo = new File(filename);
                    if (arquivo.exists()) {
                        arquivo.delete();
                    }
                    // Criar um objeto Statement para enviar instruções SQL para o banco de dados
                    stmt = conn.createStatement();

                    // Executar o SELECT e armazenar o resultado em um objeto ResultSet
                    rs = stmt.executeQuery("SELECT VERSION()");

                    // resultado versão
                    if (rs.next()) {
                        versao = rs.getString(1);
                    }
                    rs = stmt.executeQuery("select count(0) as total  FROM mysql.user WHERE user not in ('mysql.sys', 'mysql.session')");
                    if (rs.next()) {
                        total = rs.getInt(1);
                    }
                    // progressBar.setMaximum(total);

                    FileWriter escritor = new FileWriter(arquivo, true);
                    escritor.write("###### Autor: marcio28costa@hotmail.com | exportação de usúarios v.1 | Java 11 ########\n");
                    escritor.write("###### host: " + host + " | porta: " + porta + " | versão : " + versao + " #########\n");
                    escritor.write("###### Geração da consulta: " + dataHoraAtual.toString() + " ########\n");
                    escritor.close();



                    if ((versao.contains("MariaDB")) || ((parseFloat(versao.substring(0, 3))) <= 5.6)) {
                        try {
                            Processamento processamento = new Processamento();
                            processamento.exibirCaixaProcessamento();

                            ArrayList<String> result = new ArrayList<>();
                            Statement stmt1 = conn.createStatement();
                            ResultSet rs1 = stmt1.executeQuery("SELECT CONCAT('`', USER, '`@`', HOST, '`') AS user_host FROM mysql.user WHERE user not in ('mysql.sys', 'mysql.session')");
                            while (rs1.next()) {
                                String userHost = rs1.getString("user_host");
                                PreparedStatement stmt2 = conn.prepareStatement("SHOW GRANTS FOR " + userHost);
                                result.add("-- " + userHost + " --");
                                ResultSet rs2 = stmt2.executeQuery();

                                while (rs2.next()) {
                                    result.add(rs2.getString(1) + ";");
                                }
                                rs2.close();
                                stmt2.close();
                            }
                            rs1.close();
                            stmt1.close();
                            // gravação dos dados no arquivo
                            escritor = new FileWriter(arquivo, true);
                            for (String line : result) {
                                escritor.write(line + "\n");
                            }
                            escritor.write("-- exportação feita com sucesso -- ");
                            escritor.close();
                            processamento.getJframeProcessamento().dispose();
                        } catch (SQLException e1) {
                            System.out.println("Erro ao executar consulta: " + e1.getMessage());
                        } catch (IOException e1) {
                            System.out.println("Erro ao gravar dados no arquivo: " + e1.getMessage());
                        }
                    } else {
                        try {
                            Processamento processamento = new Processamento();
                            processamento.exibirCaixaProcessamento();
                            ArrayList<String> result = new ArrayList<>();
                            Statement stmt1 = conn.createStatement();
                            ResultSet rs1 = stmt1.executeQuery("SELECT CONCAT('`', USER, '`@`', HOST, '`') AS user_host FROM mysql.user WHERE user not in ('mysql.sys', 'mysql.session')");
                            while (rs1.next()) {
                                String userHost = rs1.getString("user_host");
                                PreparedStatement stmt2 = conn.prepareStatement("SHOW CREATE USER " + userHost);
                                result.add("-- " + userHost + " --");
                                ResultSet rs2 = stmt2.executeQuery();

                                while (rs2.next()) {
                                    result.add(rs2.getString(1) + ";");
                                    PreparedStatement stmt3 = conn.prepareStatement("SHOW GRANTS FOR " + userHost);
                                    ResultSet rs3 = stmt3.executeQuery();
                                    while (rs3.next()) {
                                        result.add(rs3.getString(1) + ";");
                                    }
                                }
                                rs2.close();
                                stmt2.close();
                            }
                            rs1.close();
                            stmt1.close();

                            // gravação dos dados no arquivo
                            escritor = new FileWriter(arquivo, true);
                            for (String line : result) {
                                escritor.write(line + "\n");
                            }
                            escritor.write("-- exportação feita com sucesso -- ");
                            escritor.close();
                            processamento.getJframeProcessamento().dispose();
                        } catch (SQLException e1) {
                            System.out.println("Erro ao executar consulta: " + e1.getMessage());
                        } catch (IOException e1) {
                            System.out.println("Erro ao gravar dados no arquivo: " + e1.getMessage());
                        }

                    }
                    fileButton.setEnabled(true);
                    conn.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Formulario.this, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                }

            }

        });
    }



//   public static void main(String[] args) {
//        Formulario form = new Formulario();
//        form.setVisible(true);
//    }
}
