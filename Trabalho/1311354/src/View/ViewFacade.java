package View;

import Others.ObservadorIF;

public class ViewFacade
{
	public static void iniciaTelaEscolherJogadores (ObservadorIF observador) 
	{
		EscolherJogadores frame = new EscolherJogadores();
		frame.registerObserver (observador);
		frame.registraObserverMenu(observador);
		frame.registraObserverMenu(frame);
	}
	
	public static void inicializaTelaPosicionarNavios (ObservadorIF observador, int numJogador, String nomeJogador) 
	{
		new PosicionarNavios (numJogador, nomeJogador).registerObserver (observador);
	}
	
	public static void inicializaJogo (ObservadorIF observador, String nome, int[][] meuTab, int[][] tabInimigo)
	{
		new Jogo (observador, nome, meuTab, tabInimigo).registerObserver (observador);
	}
}