import javax.swing.*;

public class Processamento  {
    private JFrame jframeProcessamento;

    public  void exibirCaixaProcessamento() {
        jframeProcessamento = new JFrame();
        jframeProcessamento.setSize(300, 150);
        jframeProcessamento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframeProcessamento.setLocationRelativeTo(null);

        JLabel jLabel = new JLabel("Processando, aguarde...");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jframeProcessamento.add(jLabel);

        jframeProcessamento.setVisible(true);
    }

    public void processar() {
        exibirCaixaProcessamento();

        // Código de processamento aqui
        // ...

        // Fechar a caixa de diálogo de processamento
        jframeProcessamento.dispose();
    }
    public JFrame getJframeProcessamento() {
        return jframeProcessamento;
    }
}