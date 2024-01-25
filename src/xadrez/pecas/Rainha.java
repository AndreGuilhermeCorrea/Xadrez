package xadrez.pecas;

import javax.swing.ImageIcon;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		if (cor == Cor.BRANCO) {
			setIconeBranco(new ImageIcon(getClass().getResource("/pictures/qw.gif")));
		} else {
			setIconePreto(new ImageIcon(getClass().getResource("/pictures/qb.gif")));
		}
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ESQUERDA
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// DIREITA
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ABAIXO
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NOROESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NORDESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SULDESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SULDOESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
}
