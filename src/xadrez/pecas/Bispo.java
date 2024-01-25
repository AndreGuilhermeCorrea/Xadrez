package xadrez.pecas;

import javax.swing.ImageIcon;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {
	// regra do bispo, podendo movimentar-se em diagonais ou ate que encontre uma peca adversaria

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	    if (cor == Cor.BRANCO) {
	        setIconeBranco(new ImageIcon(getClass().getResource("/pictures/bw.gif")));
	    } else {
	        setIconePreto(new ImageIcon(getClass().getResource("/pictures/bb.gif")));
	    }
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// NOROESTE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() -1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() -1);
		}
		if (getTabuleiro().isPosicaoVazia(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// NORDESTE
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() + 1);
		while (getTabuleiro().isPosicaoVazia(p) && !getTabuleiro().pecaNaPosicao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() +1);
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
