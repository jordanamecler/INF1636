package View;

import Others.ObservadorIF;

public class ViewFacade
{
	public static void iniciaTelaEscolherJogadores (ObservadorIF observador) 
	{
		new EscolherJogadores ().registerObserver (observador);
	}
	
	public static void inicializaTelaPosicionarNavios (ObservadorIF observador, int numJogador, String nomeJogador) 
	{
		new PosicionarNavios (numJogador, nomeJogador).registerObserver (observador);
	}
	
	public static void inicializaJogo (ObservadorIF observador)
	{
		new Jogo (observador);
	}
}