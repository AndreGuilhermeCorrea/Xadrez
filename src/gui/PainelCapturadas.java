package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class PainelCapturadas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton botaoPeca;

	private PartidaXadrez partidaXadrez;
	private TabuleiroUI tabuleiroUI;

	private static final Color DARK_COLOR = new Color(52, 52, 52);
	
	
	public PainelCapturadas(PartidaXadrez partidaXadrez) {
		this.partidaXadrez = partidaXadrez;

		initializePainelCapturadas();
	}

	private void initializePainelCapturadas() {

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(DARK_COLOR);


	}

	public void setTabuleiroUI(TabuleiroUI tabuleiroUI) {
		this.tabuleiroUI = tabuleiroUI;
	}

	public void pecasCapturadas(List<PecaXadrez> pecaCapturada) {
		removeAll();
		for (PecaXadrez peca : pecaCapturada) {
			ImageIcon icone = peca.getIcone();
			Image imagem = icone.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
			botaoPeca = new JButton(new ImageIcon(imagem));
			botaoPeca.setBorder(BorderFactory.createEmptyBorder());
			botaoPeca.setContentAreaFilled(false);
			botaoPeca.setPreferredSize(new Dimension(25, 25));

			add(botaoPeca);

			botaoPeca.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (partidaXadrez.getPromocao() != null) {
						partidaXadrez.substituirPecaPromovidaUI(peca);
						tabuleiroUI.atualizarTabuleiro();
						pecasCapturadas(partidaXadrez.getPecasCapturadas());
					}
				}
			});
		}
		revalidate();
		repaint();
	}

}