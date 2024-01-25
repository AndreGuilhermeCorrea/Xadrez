package xadrez;

import javax.swing.JOptionPane;

public class Excecao {

    public static void XadrezExcecao(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Xadrez", JOptionPane.ERROR_MESSAGE);
    }
}
