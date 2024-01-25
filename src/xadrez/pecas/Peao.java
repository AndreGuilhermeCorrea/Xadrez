package xadrez.pecas;

import javax.swing.ImageIcon;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;

		if (cor == Cor.BRANCO) {
			setIconeBranco(new ImageIcon(getClass().getResource("/pictures/pw.gif")));
		} else {
			setIconePreto(new ImageIcon(getClass().getResource("/pictures/pb.gif")));
		}
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)
					&& getTabuleiro().isPosicaoVazia(p2) && !getTabuleiro().pecaNaPosicao(p2)
					&& getContarMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().isPosicaoVazia(esquerda) && existePecaOponente(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaXadrez.getPassantVulneravel()) {
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().isPosicaoVazia(direita) && existePecaOponente(direita)
						&& getTabuleiro().peca(direita) == partidaXadrez.getPassantVulneravel()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		} else {
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)
					&& getTabuleiro().isPosicaoVazia(p2) && !getTabuleiro().pecaNaPosicao(p2)
					&& getContarMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().isPosicaoVazia(esquerda) && existePecaOponente(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaXadrez.getPassantVulneravel()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().isPosicaoVazia(direita) && existePecaOponente(direita)
						&& getTabuleiro().peca(direita) == partidaXadrez.getPassantVulneravel()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return mat;
	}

}
