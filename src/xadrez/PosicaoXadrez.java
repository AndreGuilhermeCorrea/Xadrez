package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;

		
	public PosicaoXadrez(char coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}

	protected Posicao paraPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}

	protected static PosicaoXadrez dePosicao(Posicao posicao) {
		return new PosicaoXadrez((char) ('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}

	@Override
	public String toString() {
		return "" + coluna + linha;

	}

}
