package Controller;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import View.ViewFacade;
import Model.InformacoesGlobais;
import Model.Jogador;
import Model.JogoDAO;
import Others.ObservadoIF;
import Others.ObservadorIF;

public class JogoController implements ObservadorIF, ObservadoIF
{
	private boolean jogoComecou = false;
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	
	public JogoController () 
	{	
		ViewFacade.iniciaTelaEscolherJogadores (this);
	}
	
	@Override
	public void update (String caso, Object obj) 
	{
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		Jogador jog;
		
		switch (caso)
		{
			case "escolheu_jogadores":
				escolheuJogadores (obj);
				jog = inf.getJogador (1);
				inf.setJogadorCorrente (jog);
				ViewFacade.inicializaTelaPosicionarNavios (this, 1, jog.getNome ());
				break;
				
			case "jogador1_posicionou_armas":
				jog = inf.getJogador (2);
				inf.setJogadorCorrente (jog);
				ViewFacade.inicializaTelaPosicionarNavios (this, 2, jog.getNome ());
				break;
				
			case "jogador2_posicionou_armas":
				inf.setJogadorCorrente (inf.getJogador (1));
				ViewFacade.inicializaJogo (this, inf.getJogador (1).getNome (), inf.getJogador (1).getMeuTabuleiro (), inf.getJogador (1).getTabuleiroInimigo ());
				jogoComecou = true;		
				break;
			
			case "jogo_carregado":
				JogoDAO dao = new JogoDAO();
				boolean carregou = dao.carregarJogo((File) obj);
				System.out.println("carregou " + carregou);
				if (carregou) {
					try {
						Jogador[] jogadores = dao.getJogaresCarregados();
						inf.setJogadores(jogadores[0], jogadores[1]);
						inf.setJogadorCorrente (inf.getJogador (1));
						ViewFacade.inicializaJogo (this, inf.getJogador (1).getNome (), inf.getJogador (1).getMeuTabuleiro (), inf.getJogador (1).getTabuleiroInimigo ());
						jogoComecou = true;		
					}
					catch (IndexOutOfBoundsException e) {
						System.out.println("Arquio fora do padrão.");
					}
				}
				break;
				
			case "mapa_clicado":
				if (jogoComecou) 
				{
					Point p = (Point) obj;
					boolean tiroValido = atirarNoMapa (p, inf.getJogadorCorrente ());
					
					notifyObservers ("marcar_mapa", inf.getJogadorCorrente ().getTabuleiroInimigo ());
					
					if (tiroValido)
					{
						if (inf.existeVencedor ())
						{
							notifyObservers ("jogador_ganhou", inf.getJogadorCorrente ().getNome ());
							break;
						}
						inf.getJogadorCorrente ().decrementaTiros ();
						if (inf.getJogadorCorrente ().atirouMaxVezes ())
							notifyObservers ("atirou_tres_vezes", null);
					}
				}
				break;
				
			case "trocou_jogador":
				inf.getJogadorCorrente ().setTiros ();
				if (inf.getJogadorCorrente () == inf.getJogador (1))
					inf.setJogadorCorrente (inf.getJogador (2));
				else
					inf.setJogadorCorrente (inf.getJogador (1));
				notifyObservers ("troca_de_jogador", new Object [] {inf.getJogadorCorrente ().getNome (), inf.getJogadorCorrente ().getMeuTabuleiro (), inf.getJogadorCorrente ().getTabuleiroInimigo ()});
				break;
		}
	}
	
	private void escolheuJogadores (Object obj) 
	{
		Vector <?> vector = (Vector <?>) obj;
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		Jogador j1 = inf.getJogador (1);
		Jogador j2 = inf.getJogador (2);
		j1.setNome ((String) vector.get (0));
		j2.setNome ((String) vector.get (1));
	}

	public static boolean atirarNoMapa (Point ponto, Jogador jog) 
	{
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();		
		boolean acertou = false;
		
		if (inf.getJogadorCorrente () == inf.getJogador (1))
		{
			if (inf.getJogador (1).jaAtirouNaPosicao (ponto.x, ponto.y) == false)
			{
				acertou = inf.getJogador (2).getConteudoMeuMapa (ponto);
				inf.getJogador (2).marcarMeuTabuleiro (ponto.x, ponto.y);
				
				if (inf.getJogador (2).acabaramArmas ())
				{
					inf.setExisteVencedor ();
				}
			}
			else
				return false;
			
		}
		else
		{
			if (inf.getJogador (2).jaAtirouNaPosicao (ponto.x, ponto.y) == false)
			{
				acertou = inf.getJogador (1).getConteudoMeuMapa (ponto);
				inf.getJogador (1).marcarMeuTabuleiro (ponto.x, ponto.y);
				
				if (inf.getJogador (1).acabaramArmas ())
				{
					inf.setExisteVencedor ();
				}
			}
			else
				return false;
		}
		
		inf.getJogadorCorrente ().marcarTabuleiroInimigo (ponto.x, ponto.y, acertou);
		return true;
	}
	
	@Override
	public void registerObserver (ObservadorIF observer)
	{
		observers.add (observer);	
	}

	@Override
	public void removeObserver (ObservadorIF observer)
	{
		observers.remove (observer);	
	}

	@Override
	public void notifyObservers (String mensagem, Object obj)
	{
		ListIterator <ObservadorIF> li = observers.listIterator ();
		while (li.hasNext ())
		{
			ObservadorIF ob = (ObservadorIF) li.next ();
			ob.update (mensagem, obj);
		}
	}
}