package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class TabuleiroUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int LINHAS = 8;
	private static final int COLUNAS = 8;

	private JButton[][] ladrilho = new JButton[LINHAS][COLUNAS];
	private static final Color LIGHT_COLOR = new Color(240, 240, 240);
	private static final Color DARK_COLOR = new Color(52, 52, 52);
	private static final Color ORANGE = new Color(243, 212, 11);
	private static final Color GREEN = new Color(148, 236, 36);

	private Color[][] originalColor = new Color[LINHAS][COLUNAS];
	private boolean isLightColor = true;
	private boolean selected;
	private JButton botaoSelecionado;

	private PainelCapturadas painelCapturadas;
	private PartidaXadrez partidaXadrez;
	private PosicaoXadrez posicaoOrigem;
	private PosicaoXadrez posicaoDestino;
	private PainelInformacoes painelInformacoes;

	public TabuleiroUI(PartidaXadrez partidaXadrez, PainelCapturadas painelCapturadas,
			PainelInformacoes painelInformacoes) {
		this.partidaXadrez = partidaXadrez;
		this.painelInformacoes = painelInformacoes;
		this.painelCapturadas = painelCapturadas;

		criaTabuleiro();

	}

	private void criaTabuleiro() {
		setLayout(new GridLayout(LINHAS, COLUNAS));

		for (int linha = 0; linha < LINHAS; linha++) {
			isLightColor = !isLightColor;
			for (int coluna = 0; coluna < COLUNAS; coluna++) {
				ladrilho[linha][coluna] = new JButton();
				originalColor[linha][coluna] = isLightColor ? LIGHT_COLOR : DARK_COLOR;
				ladrilho[linha][coluna].setBackground(originalColor[linha][coluna]);
				int finalLinha = linha;
				int finalColuna = coluna;
				ladrilho[linha][coluna].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						clickBotao(finalLinha, finalColuna);
					}
				});
				add(ladrilho[linha][coluna]);
				isLightColor = !isLightColor;
			}
		}
		inicializarTabuleiro();

	}

	private void clickBotao(int linha, int coluna) {
		JButton botaoClicado = ladrilho[linha][coluna];
		restaurarBotoes();
		if (!selected) {
			posicaoOrigem = converteParaPosicaoXadrez(linha, coluna);
			PecaXadrez peca = partidaXadrez.getPecas()[linha][coluna];

			if (peca != null && peca.getCor() == partidaXadrez.getJogadorAtual()) {
				mostrarMovimentosPossiveis(linha, coluna);
				botaoClicado.setBackground(ORANGE);
				selected = true;
				botaoSelecionado = botaoClicado;
			}
		} else {
			if (botaoClicado != botaoSelecionado) {
				posicaoDestino = converteParaPosicaoXadrez(linha, coluna);
				moverPeca(posicaoOrigem, posicaoDestino);
				atualizarTabuleiro();
				restaurarCoresOriginais();

				selected = false;
				posicaoOrigem = null;
			} else {
				restaurarCoresOriginais();
				selected = false;
			}
		}
	}

	private void mostrarMovimentosPossiveis(int linha, int coluna) {
		PosicaoXadrez posicaoOrigem = converteParaPosicaoXadrez(linha, coluna);
		boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(posicaoOrigem);
		for (int i = 0; i < LINHAS; i++) {
			for (int j = 0; j < COLUNAS; j++) {
				if (movimentosPossiveis[i][j]) {
					ladrilho[i][j].setBackground(GREEN);
				}
			}
		}
	}

	private PosicaoXadrez converteParaPosicaoXadrez(int linha, int col) {
		char coluna = (char) ('a' + col);
		int linhaXadrez = 8 - linha;
		return new PosicaoXadrez(coluna, linhaXadrez);
	}

	private void restaurarCoresOriginais() {
		for (int i = 0; i < LINHAS; i++) {
			for (int j = 0; j < COLUNAS; j++) {
				ladrilho[i][j].setBackground(originalColor[i][j]);
			}
		}
	}

	private void restaurarBotoes() {
		for (int i = 0; i < LINHAS; i++) {
			for (int j = 0; j < COLUNAS; j++) {
				ladrilho[i][j].setEnabled(true);
			}
		}
	}

	public void atualizarTabuleiro() {
		PecaXadrez[][] pecas = partidaXadrez.getPecas();
		if (!partidaXadrez.getXequeMate()) {
			for (int i = 0; i < LINHAS; i++) {
				for (int j = 0; j < COLUNAS; j++) {
					PecaXadrez peca = pecas[i][j];
					JButton botao = ladrilho[i][j];

					if (peca != null) {
						botao.setIcon(peca.getIcone());
					} else {
						botao.setIcon(null);
					}
				}
			}
		}
		this.revalidate();
		this.repaint();
	}

	private void moverPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {

		if (!partidaXadrez.validarMovimento(posicaoOrigem, posicaoDestino)) {
			JOptionPane.showMessageDialog(this, "Movimento inválido! Tente novamente.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		partidaXadrez.executarMovimentoXadrez(posicaoOrigem, posicaoDestino);
		painelCapturadas.pecasCapturadas(partidaXadrez.getPecasCapturadas());
		atualizarTabuleiro();
		painelInformacoes.trocarTurno();

		if (partidaXadrez.getPromocao() != null) {
			JOptionPane.showMessageDialog(this, "Escolha uma peça dentre as peças capturadas.", "Promoção",
					JOptionPane.INFORMATION_MESSAGE);
		}

		testeXeque();
	}

	private void testeXeque() {

		// Verifica se o jogo terminou com xeque-mate
		if (partidaXadrez.getXequeMate()) {
			// Se xeque-mate, exibe uma mensagem indicando o vencedor
			String vencedor = partidaXadrez.getJogadorAtual() == Cor.BRANCO ? "Pretas" : "Brancas";
			JOptionPane.showMessageDialog(this, "Xeque-mate! Vencedor: " + vencedor, "Fim do jogo",
					JOptionPane.INFORMATION_MESSAGE);
			// Reiniciar o jogo
			partidaXadrez.reiniciarPartida();
			limparTabuleiro();
			inicializarTabuleiro();
			restaurarBotoes();
		}

	}

	private void limparTabuleiro() {

		painelInformacoes.reiniciarTemporizadores();

		for (int i = 0; i < LINHAS; i++) {
			for (int j = 0; j < COLUNAS; j++) {
				ladrilho[i][j].setIcon(null);
				ladrilho[i][j].setBackground(originalColor[i][j]);
			}
		}

	}

	private void inicializarTabuleiro() {

		PecaXadrez[][] pecas = partidaXadrez.getPecas();
		for (int i = 0; i < LINHAS; i++) {
			for (int j = 0; j < COLUNAS; j++) {
				if (pecas[i][j] != null) {
					ladrilho[i][j].setIcon(pecas[i][j].getIcone());
				}
			}
		}
	}

}
