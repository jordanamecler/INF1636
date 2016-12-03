package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Model.EstadoPosicionamento;
import Others.ObservadoIF;
import Others.ObservadorIF;

public class Jogador implements ObservadoIF
{
	private String nome;
	private int[][] meuTabuleiro = new int[15][15];
	private int[][] tabuleiroInimigo = new int[15][15];
	private int[][] tabuleiroAux = new int[15][15];
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	private List <Arma> armas = new ArrayList <Arma> ();
	
	public String getNome ()
	{
		return nome;
	}

	public void setNome (String nome)
	{
		this.nome = nome;
	}

	public int[][] getTabuleiroInimigo ()
	{
		return tabuleiroInimigo;
	}

	public int[][] getMeuTabuleiro ()
	{
		return meuTabuleiro;
	}
	
	public int[][] getTabuleiroAux ()
	{
		return tabuleiroAux;
	}
	
	public void marcarMeuTabuleiro (int x, int y)
	{
		this.meuTabuleiro[x][y] = 1;
	}
	
	public void marcarTabuleiroInimigo (int x, int y)
	{
		this.tabuleiroInimigo[x][y] = 1;
	}
	
	public boolean posicionarArmaNoTabuleiro (int x, int y, Arma a, boolean girando)
	{
		int[][] pontosDaArma = a.getPontos ();

		copiaTabuleiro (tabuleiroAux, meuTabuleiro);
		
		if (girando)
		{
			y = a.getPrimeiroPontoNoMapa ().x;
			x = a.getPrimeiroPontoNoMapa ().y;
		}
		
		if (x + a.getLargura () - 1 > 14)
			return false;
		if (y - a.getAltura () < -1)
			return false;
		
		for (int i = 0; i < 5; i++ )
		{
			for (int j = 1; j >= 0; j-- )
			{	
				if (pontosDaArma[j][i] == 1)
				{
					int posX = x + i, posY = y + j - 1;
					
					if (girando)
					{
						int aux = posX;
						posX = posY;
						posY = aux;
					}
					
					if (!testaPosicaoValida (posX, posY, a))
					{
						copiaTabuleiro (tabuleiroAux, meuTabuleiro);
						return false;
					}
					tabuleiroAux[posX][posY] = a.getTipoDeArma ().ordinal ();
				}
			}
		}
		
		for (Arma arma: armas)
			if (arma == a)
			{
				if (girando)
					arma.setPrimeiroPontoNoMapa (y, x); 
				else
					arma.setPrimeiroPontoNoMapa (x, y);
				return true;
			}
		return true;
	}
	
	public boolean testaPosicaoValida (int x, int y, Arma arma)
	{
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				if (x + i >= 0 && x + i < 15 && y + j >= 0 && y + j < 15)
					if (meuTabuleiro[x + i][y + j] != 0)
						return false;
			}
		}
		
		return true;
	}
	
	public void atualizaTabuleiro ()
	{
		copiaTabuleiro (meuTabuleiro, tabuleiroAux);
	}
	
	private void copiaTabuleiro (int[][] tabuleiro1, int[][] tabuleiro2)
	{
		for(int i=0; i< tabuleiro2.length; i++)
			  for (int j=0; j< tabuleiro2[i].length; j++)
				  tabuleiro1[i][j] = tabuleiro2[i][j];
	}
	
	public Arma getArmaEmEstado (EstadoPosicionamento estado)
	{
		for (Arma a: armas)
		{
			if (a.getEstadoPosicionamento () == estado)
				return a;
		}
		return null;
	}
	
	public boolean podeSelecionarArma ()
	{
		for (Arma a: armas)
		{
			if (a.getEstadoPosicionamento () == EstadoPosicionamento.EmTransicao ||
				a.getEstadoPosicionamento () == EstadoPosicionamento.Girando)
				return false;
		}
		
		return true;
	}
	
	public List <Arma> getListaArmas ()
	{
		return armas;
	}
	
	public void verificaArmasUsadas ()
	{
		for (Arma arma: armas)
			if (arma.getEstadoPosicionamento() != EstadoPosicionamento.Posicionada)
				//return;
		
		this.notifyObservers ("posicionou_todas_armas");
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
    public void notifyObservers (String mensagem)
    {
    	ListIterator<ObservadorIF> li = observers.listIterator();
		
		while(li.hasNext()) {
			ObservadorIF ob = (ObservadorIF) li.next();
			System.out.println ("Notificando observers!");
			ob.update (mensagem, true);
			li.remove();
		}
    }
}