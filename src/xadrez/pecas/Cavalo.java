package xadrez.pecas;

import javax.swing.ImageIcon;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		if (cor == Cor.BRANCO) {
			setIconeBranco(new ImageIcon(getClass().getResource("/pictures/cw.gif")));
		} else {
			setIconePreto(new ImageIcon(getClass().getResource("/pictures/cb.gif")));
		}
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}