package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xadrez.PartidaXadrez;

public class TelaXadrez extends JFrame {
	private static final long serialVersionUID = 1L;

	private PartidaXadrez partidaXadrez;
	private TabuleiroUI tabuleiroUI;
	private PainelInformacoes painelInformacoes;
	private PainelCapturadas painelCapturadas;
	

	private static final int COLUNAS = 8;
	private static final int LINHAS = 8;
	private static final Color BLACK_COLOR = new Color(0, 0, 0);
	private static final Color LIGHT_COLOR = new Color(240, 240, 240);
	private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 14);

	public TelaXadrez(PartidaXadrez partidaXadrez) {
		this.partidaXadrez = partidaXadrez;

		painelInformacoes = new PainelInformacoes(this.partidaXadrez);
		painelCapturadas = new PainelCapturadas(this.partidaXadrez);
		tabuleiroUI = new TabuleiroUI(this.partidaXadrez, this.painelCapturadas, this.painelInformacoes);
		this.painelCapturadas.setTabuleiroUI(this.tabuleiroUI);
		initializeUI();
	}

	private void initializeUI() {
		

		setTitle("Xadrez");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 900);
		setLayout(new BorderLayout());
		
		JPanel painelPrincipal = new JPanel(new BorderLayout());
		painelPrincipal.add(tabuleiroUI, BorderLayout.CENTER);
		
		
		add(painelPrincipal, BorderLayout.CENTER);
		
		
		JPanel colunasDesc = new JPanel(new GridLayout(1, COLUNAS));
		addColunas(colunasDesc);
		JPanel linhasDescs = new JPanel(new GridLayout(LINHAS, 1));
		addLinhas(linhasDescs);
		
		
		
		JPanel painelSul = new JPanel(new BorderLayout());
		
		add(linhasDescs, BorderLayout.WEST);
		
		painelInformacoes.add(painelCapturadas);
		
		painelSul.add(colunasDesc, BorderLayout.NORTH);
		painelSul.add(painelInformacoes, BorderLayout.CENTER);
		add(painelSul, BorderLayout.SOUTH);

	}

	private void addColunas(JPanel colunasDesc) {
		char label = 'A';
		for (int col = 0; col < COLUNAS; col++) {
			JLabel lbColuna = new JLabel(Character.toString(label));
			lbColuna.setHorizontalAlignment(SwingConstants.CENTER);
			lbColuna.setOpaque(true);
			lbColuna.setBackground(BLACK_COLOR);
			lbColuna.setFont(LABEL_FONT);
			lbColuna.setForeground(LIGHT_COLOR);
			colunasDesc.add(lbColuna);
			label++;
		}
	}

	private void addLinhas(JPanel linhasDescs) {
		for (int linha = LINHAS; linha >= 1; linha--) {
			JLabel lbLinha = new JLabel(Integer.toString(linha));
			lbLinha.setHorizontalAlignment(SwingConstants.CENTER);
			lbLinha.setOpaque(true);
			lbLinha.setBackground(BLACK_COLOR);
			lbLinha.setFont(LABEL_FONT);
			lbLinha.setForeground(LIGHT_COLOR);
			linhasDescs.add(lbLinha);
		}
	}
}
