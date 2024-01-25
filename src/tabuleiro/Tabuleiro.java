package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;

	private Peca[][] pecas;

	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void localPeca(Peca peca, Posicao posicao) {
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	public Peca removePeca(Posicao posicao) {
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}

	private boolean isPosicaoOcupada(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	public boolean isPosicaoVazia(Posicao posicao) {
		return isPosicaoOcupada(posicao.getLinha(), posicao.getColuna());
	}

	public boolean pecaNaPosicao(Posicao posicao) {
		return peca(posicao) != null;
	}

	public void limparTabuleiro() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				pecas[i][j] = null;
			}
		}
	}

}
