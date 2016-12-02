package Controller;

import java.util.Vector;

import View.ViewFacade;
import Model.InformacoesGlobais;
import Model.Jogador;
import Others.ObservadorIF;

public class JogoController implements ObservadorIF {

	public JogoController() 
	{	
		ViewFacade.iniciaTelaEscolherJogadores(this);
	}
	
	@Override
	public void update(String caso, Object obj) 
	{
		System.out.println(obj);
		
		switch(caso) {
			case "escolheu_jogadores":
				this.escolheuJogadores(obj);
				ViewFacade.inicializaTelaPosicionarNavios(this, 1);
				break;
			case "jogador1_posicionou_armas":
				ViewFacade.inicializaTelaPosicionarNavios(this, 2);
				break;
			case "jogador2_posicionou_armas":
				ViewFacade.inicializaJogo();
				break;
		}
	}
	
	private void escolheuJogadores(Object obj) 
	{
		Vector vector = (Vector) obj;
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		Jogador j1 = inf.getJogador (1);
		Jogador j2 = inf.getJogador (2);
		j1.setNome ((String) vector.get(0));
		j2.setNome ((String) vector.get(1));
	}
}
