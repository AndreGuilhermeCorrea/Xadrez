package xadrez.pecas;

import javax.swing.ImageIcon;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
		if (cor == Cor.BRANCO) {
			setIconeBranco(new ImageIcon(getClass().getResource("/pictures/rw.gif")));
		} else {
			setIconePreto(new ImageIcon(getClass().getResource("/pictures/rb.gif")));
		}
	}

	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	private boolean testeTorreRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContarMovimentos() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		// ACIMA
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ESQUERDA
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// DIREITA
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ABAIXO
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NOROESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NORDESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SUDESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// SUDOESTE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().isPosicaoVazia(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		if (getContarMovimentos() == 0 && !partidaXadrez.getXeque()) {
			Posicao posicaoTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testeTorreRoque(posicaoTorre1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}

			Posicao posicaoTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);

			if (testeTorreRoque(posicaoTorre2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null
						&& getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}

		}
		return mat;
	}
}
