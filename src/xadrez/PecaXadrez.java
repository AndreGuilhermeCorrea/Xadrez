package xadrez;

import javax.swing.ImageIcon;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
    private ImageIcon iconeBranco;
    private ImageIcon iconePreto;
	private int contarMovimentos;

	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContarMovimentos() {
		return contarMovimentos;
	}
	
	public void aumentarContagemMovimentos() {
		contarMovimentos++;
		
	}
	
	public void diminuirContagemMovimentos() {
		contarMovimentos--;
	}
	

	public PosicaoXadrez getPosicaoXadrez() {

		return PosicaoXadrez.dePosicao(posicao);	
	}

	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
	
    public void setIconeBranco(ImageIcon icone) {
        this.iconeBranco = icone;
    }

    public void setIconePreto(ImageIcon icone) {
        this.iconePreto = icone;
    }

    public ImageIcon getIcone() {
        return cor == Cor.BRANCO ? iconeBranco : iconePreto;
    }


}
