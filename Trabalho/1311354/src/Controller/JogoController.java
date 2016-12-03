package Controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import View.ViewFacade;
import Model.InformacoesGlobais;
import Model.Jogador;
import Model.JogoFacade;
import Others.ObservadoIF;
import Others.ObservadorIF;

public class JogoController implements ObservadorIF, ObservadoIF{
	
	boolean jogoComecou = false;
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	
	public JogoController() 
	{	
		ViewFacade.iniciaTelaEscolherJogadores(this);
	}
	
	@Override
	public void update(String caso, Object obj) 
	{
		System.out.println(obj);
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		
		switch(caso) {
			case "escolheu_jogadores":
				this.escolheuJogadores(obj);
				ViewFacade.inicializaTelaPosicionarNavios(this, 1);
				break;
			case "jogador1_posicionou_armas":
				ViewFacade.inicializaTelaPosicionarNavios(this, 2);
				break;
			case "jogador2_posicionou_armas":
				inf.setJogadorCorrente(inf.getJogador(1));
				ViewFacade.inicializaJogo(this);
				this.jogoComecou = true;
				break;
			case "mapa_clicado":
				if (this.jogoComecou) 
				{
					Point p = (Point) obj;
					System.out.println("Ataque na posicao " + p);
					boolean tiroValido = JogoFacade.atirarNoMapa(p, inf.getJogadorCorrente());
					this.notifyObservers("marcar_mapa", inf.getJogadorCorrente().getTabuleiroInimigo());
				}
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

	@Override
	public void registerObserver(ObservadorIF observer) {
		observers.add (observer);
		
	}

	@Override
	public void removeObserver(ObservadorIF observer) {
		observers.remove (observer);
		
	}

	@Override
	public void notifyObservers(String mensagem, Object obj) {
		ListIterator<ObservadorIF> li = observers.listIterator();
		System.out.println("notifica acerto ");
		while(li.hasNext()) {
			ObservadorIF ob = (ObservadorIF) li.next();
			ob.update (mensagem, obj);
		}
		
	}
}
