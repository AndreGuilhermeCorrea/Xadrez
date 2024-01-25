package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import xadrez.Cor;
import xadrez.PartidaXadrez;

public class PainelInformacoes extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel turno;
	private JLabel jogadorAtual;
	private JLabel tempoTotal;
	private JLabel tempoBranco;
	private JLabel tempoPreto;
	
	private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 14);
	private static final Color BLACK_COLOR = new Color(0, 0, 0);
	private static final Color LIGHT_COLOR = new Color(240, 240, 240);
	private PartidaXadrez partidaXadrez;

	private Timer timerBranco;
	private Timer timerPreto;
	private Timer timerTotal;
	private int vTempoBranco = 0;
	private int vTempoPreto = 0;
	private int vTempoTotal = 0;

	public PainelInformacoes(PartidaXadrez partidaXadrez) {
		this.partidaXadrez = partidaXadrez;
		
		initializePainelInformacoes();
	}

	private void initializePainelInformacoes() {
		
		setLayout(new GridLayout(6, 1));
		setBackground(BLACK_COLOR);
		


		// Inicializa os componentes do painel
		turno = new JLabel("Turno: " + partidaXadrez.getTurno());
		turno.setForeground(LIGHT_COLOR);
		turno.setFont(LABEL_FONT);

		jogadorAtual = new JLabel(
				"Jogador Atual: " + (partidaXadrez.getJogadorAtual() == Cor.BRANCO ? "WHITE" : "BLACK"));
		jogadorAtual.setForeground(LIGHT_COLOR);
		jogadorAtual.setFont(LABEL_FONT);

		tempoTotal = new JLabel("Tempo de Jogo: 00:00:00");
		tempoTotal.setForeground(LIGHT_COLOR);
		tempoTotal.setFont(LABEL_FONT);

		tempoBranco = new JLabel("Tempo Branco: 00:00:00");
		tempoBranco.setForeground(LIGHT_COLOR);
		tempoBranco.setFont(LABEL_FONT);

		tempoPreto = new JLabel("Tempo Preto: 00:00:00");
		tempoPreto.setForeground(LIGHT_COLOR);
		tempoPreto.setFont(LABEL_FONT);

		timerBranco = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vTempoBranco++;
				tempoBranco.setText("Tempo Branco: " + formatarTempo(vTempoBranco));
			}
		});

		timerPreto = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vTempoPreto++;
				tempoPreto.setText("Tempo Preto: " + formatarTempo(vTempoPreto));
			}
		});

		timerTotal = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vTempoTotal++;
				tempoTotal.setText("Tempo de jogo: " + formatarTempo(vTempoTotal));
			}
		});

		add(tempoTotal);
		add(turno);
		add(jogadorAtual);
		add(tempoBranco);
		add(tempoPreto);



		// Iniciar os timers
		timerBranco.start();
		timerTotal.start();

	}

	private String formatarTempo(int tempoEmSegundos) {
		int horas = tempoEmSegundos / 3600;
		int minutos = (tempoEmSegundos % 3600) / 60;
		int segundos = tempoEmSegundos % 60;
		return String.format("%02d:%02d:%02d", horas, minutos, segundos);
	}

	private void atualizarTurno() {

		turno.repaint();
		turno.revalidate();
		turno.setText("Turno: " + partidaXadrez.getTurno());

	}

	private void atualizarJogadorAtual() {
		jogadorAtual.setText(partidaXadrez.getJogadorAtual() == Cor.BRANCO ? "WHITE" : "BLACK");
		jogadorAtual.repaint();
		jogadorAtual.revalidate();
	}

	private void atualizarTempoTotal(String tempo) {
		tempoTotal.setText("Tempo de Jogo: " + tempo);
	}

	private void atualizarTempoBranco(String tempo) {
		tempoBranco.setText("Tempo Branco: " + tempo);
	}

	private void atualizarTempoPreto(String tempo) {
		tempoPreto.setText("Tempo Preto: " + tempo);
	}

	private void alternarTimer() {
		if (timerBranco.isRunning()) {
			timerBranco.stop();
			timerPreto.start();
		} else {
			timerPreto.stop();
			timerBranco.start();
		}
	}

	public void reiniciarTemporizadores() {
		vTempoBranco = 0;
		vTempoPreto = 0;
		vTempoTotal = 0;
		atualizarTempoBranco(formatarTempo(vTempoBranco));
		atualizarTempoPreto(formatarTempo(vTempoPreto));
		atualizarTempoTotal(formatarTempo(vTempoTotal));
		timerBranco.restart();
		timerPreto.stop();
		timerTotal.restart();
	}

	public void trocarTurno() {
		atualizarTurno();
	    atualizarJogadorAtual();
	    alternarTimer();
	}

	
	
}