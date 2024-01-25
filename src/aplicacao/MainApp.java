package aplicacao;

import javax.swing.SwingUtilities;

import gui.TelaXadrez;
import xadrez.PartidaXadrez;

public class MainApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			PartidaXadrez partidaXadrez = new PartidaXadrez();
			TelaXadrez telaXadrez = new TelaXadrez(partidaXadrez);
			telaXadrez.setVisible(true);
		});
	}
}