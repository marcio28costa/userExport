

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // inicia o formulario
        SwingUtilities.invokeLater(() -> {
            Formulario formulario = new Formulario();
            formulario.setVisible(true);
        });
    }
}