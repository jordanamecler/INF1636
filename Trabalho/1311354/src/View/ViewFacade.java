package View;

import Others.ObservadorIF;

public class ViewFacade {

	public static void iniciaTelaEscolherJogadores(ObservadorIF observador) 
	{
		
		new EscolherJogadores ().registerObserver(observador);;
	}
	
	public static void inicializaTelaPosicionarNavios(ObservadorIF observador,int numJogador) 
	{
		new PosicionarNavios (numJogador).registerObserver(observador);;
	}
	
	public static void inicializaJogo(ObservadorIF observador) {
		new Jogo (observador);
	}
}
