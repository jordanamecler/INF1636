package Model;

import java.awt.Point;
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
	private int numTiros = 3;
	
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
	
	public boolean getConteudoMeuMapa (Point p)
	{
		if (meuTabuleiro[p.x][p.y] != 0)
			return true;
		
		return false;
	}
	
	public void marcarMeuTabuleiro (int x, int y)
	{
		if (meuTabuleiro[x][y] == 0)
			meuTabuleiro[x][y] = 7;
		else if (meuTabuleiro[x][y] != 7 && meuTabuleiro[x][y] == 6)
			meuTabuleiro[x][y] = 6;
	}
	
	public boolean jaAtirouNaPosicao (int x, int y) 
	{
		if (tabuleiroInimigo[x][y] == 6 || tabuleiroInimigo[x][y] == 7) 
			return true;
		
		return false;
	}
	
	public void marcarTabuleiroInimigo (int x, int y, boolean acertou)
	{
		if (acertou)
			tabuleiroInimigo[x][y] = 6;
		else
			tabuleiroInimigo[x][y] = 7;
	}
	
	public boolean posicionarArmaNoTabuleiro (int x, int y, Arma a, boolean girando)
	{
		int[][] pontosDaArma = a.getPontos ();
		
		if (girando && a.getDirecao () == Direcao.Padrao)
		{
			if (y - a.getLargura () + 1 < 0)
				return true;
			if (x - a.getAltura () + 1 < 0)
				return true;
		}
		else if (girando && a.getDirecao () == Direcao.NoventaGraus)
		{
			if (x - a.getLargura () + 1 < 0)
				return true;
			if (y + a.getAltura () - 1 > 14)
				return true;
		}
		else if (girando && a.getDirecao () == Direcao.CentoEOitentaGraus)
		{
			if (y + a.getLargura () - 1 > 14)
				return true;
		}
		else
		{
			if (x + a.getLargura () - 1 > 14)
				return false;
			if (y - a.getAltura () < -1)
				return false;
		}
		
		int[][] tabuleiroParcial = new int[15][15];
		
		copiaTabuleiro (tabuleiroParcial, tabuleiroAux);
		copiaTabuleiro (tabuleiroAux, meuTabuleiro);
		
		for (int i = 0; i < 5; i++ )
		{
			for (int j = 1; j >= 0; j-- )
			{	
				if (pontosDaArma[j][i] == 1)
				{
					int posX = x + i, posY = y + j - 1;
					
					if (girando)
					{
						switch (a.getDirecao ())
						{
							case Padrao:
								posX = x + j - 1;
								posY = y - i;
								break;
							case NoventaGraus:
								posX = x - i;
								posY = y - j + 1;
								break;
							case CentoEOitentaGraus:
								posX = x - j + 1;
								posY = y + i;
								break;
							default:
								break;
						}
					}
					
					if (!testaPosicaoValida (posX, posY, a))
					{
						if (!girando)
						{
							copiaTabuleiro (tabuleiroAux, meuTabuleiro);
							return false;
							
						}
						else
						{
							copiaTabuleiro (tabuleiroAux, tabuleiroParcial);
							return true;
						}
					}
					tabuleiroAux[posX][posY] = a.getTipoDeArma ().ordinal ();
				}
			}
		}

		for (Arma arma: armas)
		{
			if (arma == a)
			{
				if (girando)
					arma.giraDirecao ();
				arma.setPrimeiroPontoNoMapa (x, y);
				return true;
			}
		}
		
		return false;
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
		
		this.notifyObservers ("posicionou_todas_armas", null);
	}
	
	public void decrementaTiros ()
	{
		numTiros--;
	}
	
	public void setTiros ()
	{
		numTiros = 3;
	}
	
	public int getNumTiros ()
	{
		return numTiros;
	}
	
	public boolean atirouMaxVezes ()
	{
		if (numTiros <= 0)
			return true;
		return false;
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
			System.out.println ("Notificando observers!");
			ob.update (mensagem, true);
			li.remove ();
		}
    }
}