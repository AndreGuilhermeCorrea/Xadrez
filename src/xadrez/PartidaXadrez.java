package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	private PecaXadrez passantVulneravel;
	private PecaXadrez promocao;
	private List<Peca> pecasTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}

	public PecaXadrez getPassantVulneravel() {
		return passantVulneravel;
	}

	public PecaXadrez getPromocao() {
		return promocao;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		iniciarPartida();
	}
	
	private void iniciarPartida() {
		novaPosicaoPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		novaPosicaoPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		novaPosicaoPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));

		novaPosicaoPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		novaPosicaoPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		novaPosicaoPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
	}
	
	public void reiniciarPartida() {
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		xeque = false;
		xequeMate = false;
		passantVulneravel = null;
		promocao = null;
		tabuleiro.limparTabuleiro();
		pecasTabuleiro.clear();
		pecasCapturadas.clear();
		iniciarPartida();
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.paraPosicao();
		validacaoPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez executarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validacaoPosicaoOrigem(origem);
		validacaoPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMover(origem, destino);
		if (testeXeque(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
		}
		PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(destino);
		promocao = null;
		if (pecaMovida instanceof Peao) {
			if ((pecaMovida.getCor() == Cor.BRANCO && destino.getLinha() == 0)
					|| (pecaMovida.getCor() == Cor.PRETO && destino.getLinha() == 7)) {
				promocao = (PecaXadrez) tabuleiro.peca(destino);
			}
		}
		xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;
		if (testeXequeMate(oponente(jogadorAtual))) {
			xequeMate = true;
		} else {
			proximoTurno();
		}
		if (pecaMovida instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			passantVulneravel = pecaMovida;
		} else {
			passantVulneravel = null;
		}
		return (PecaXadrez) pecaCapturada;
	}

	public void substituirPecaPromovidaUI(PecaXadrez pecaEscolhida) {
		Posicao pos = promocao.getPosicaoXadrez().paraPosicao();
		tabuleiro.removePeca(pos); // Remove o peão
		tabuleiro.localPeca(pecaEscolhida, pos); // Coloca a peça capturada no lugar
		pecasTabuleiro.add(pecaEscolhida);
		promocao = null; // Reset da promoção
	}

	private Peca fazerMover(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(origem);
		p.aumentarContagemMovimentos();
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.localPeca(p, destino);
		if (pecaCapturada != null) {
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		// roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			// retirar a torre do local e colocar em nova posicao
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemTorre);
			// posicao destino torre
			tabuleiro.localPeca(torre, destinoTorre);
			torre.aumentarContagemMovimentos();
		}
		// roque Grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			// retirar a torre do local e colocar em nova posicao
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemTorre);
			// posicao destino torre
			tabuleiro.localPeca(torre, destinoTorre);
			torre.aumentarContagemMovimentos();
		}
		// Passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao peaoPosicao;
				if (p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				} else {
					peaoPosicao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removePeca(peaoPosicao);
				pecasCapturadas.add(pecaCapturada);
				pecasTabuleiro.remove(pecaCapturada);
			}
		}
		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(destino);
		p.diminuirContagemMovimentos();
		tabuleiro.localPeca(p, origem);
		if (pecaCapturada != null) {
			tabuleiro.localPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
		}
		// roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			// retirar a torre do local e colocar em nova posicao
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoTorre);
			// posicao destino torre
			tabuleiro.localPeca(torre, origemTorre);
			torre.diminuirContagemMovimentos();
		}
		// roque Grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			// retirar a torre do local e colocar em nova posicao
			PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoTorre);
			// posicao destino torre
			tabuleiro.localPeca(torre, origemTorre);
			torre.diminuirContagemMovimentos();
		}
		// Passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == passantVulneravel) {
				PecaXadrez peao = (PecaXadrez) tabuleiro.removePeca(destino);
				Posicao peaoPosicao;
				if (p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(3, destino.getColuna());
				} else {
					peaoPosicao = new Posicao(4 - 1, destino.getColuna());
				}
				tabuleiro.localPeca(peao, peaoPosicao);
			}
		}
	}

	public boolean validarMovimento(PosicaoXadrez origemXadrez, PosicaoXadrez destinoXadrez) {
		Posicao origem = origemXadrez.paraPosicao();
		Posicao destino = destinoXadrez.paraPosicao();
		if (!validacaoPosicaoOrigem(origem) || !validacaoPosicaoDestino(origem, destino)) {
			return false;
		}
		return true;
	}

	private boolean validacaoPosicaoOrigem(Posicao origem) {
		if (!tabuleiro.pecaNaPosicao(origem)) {
			return false;
		}
		if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(origem)).getCor())
			return false;
		if (!tabuleiro.peca(origem).umPossivelMovimento()) {
			return false;
		}
		return true;
	}

	private boolean validacaoPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			return false;
		}
		return true;
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaXadrez rei(Cor cor) {
		List<Peca> list = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Erro! ");
	}

	private boolean testeXeque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peca> pecasOponente = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecasOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeXequeMate(Cor cor) {
		if (!testeXeque(cor)) {
			return false;
		}
		List<Peca> list = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez) p).getPosicaoXadrez().paraPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerMover(origem, destino);
						boolean testeXeque = testeXeque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void novaPosicaoPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.localPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasTabuleiro.add(peca);
	}

	public boolean[][] movimentosPossiveis(Posicao aPosicao) {
		validacaoPosicaoOrigem(aPosicao);
		return tabuleiro.peca(aPosicao).movimentosPossiveis();
	}

	public List<PecaXadrez> getPecasCapturadas() {
		return pecasCapturadas.stream().map(x -> (PecaXadrez) x).collect(Collectors.toList());
	}



}
