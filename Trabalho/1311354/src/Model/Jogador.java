package Model;

import java.util.ArrayList;
import java.util.List;

import Others.ObservadoIF;
import Others.ObservadorIF;

public class Jogador implements ObservadoIF
{
	private String nome;
	private int[][] meuTabuleiro = new int[15][15];
	private int[][] tabuleiroInimigo = new int[15][15];
	private boolean[] armasPosicionadas = new boolean[15];
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	
	public List<Arma> armas = new ArrayList <Arma> ();
	
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
	
	public void marcarMeuTabuleiro (int x, int y)
	{
		this.meuTabuleiro[x][y] = 1;
	}
	
	public void marcarTabuleiroInimigo (int x, int y)
	{
		this.tabuleiroInimigo[x][y] = 1;
	}
	
	public Boolean posicionarArmaNoTabuleiro(int x, int y, Arma a) {
		int[][] pontosDaArma = a.getPontos();
		int[][] tabuleiroAux = new int[15][15];
		// TODO: fazer os testes de limites do mapa e de proximidade com outras armas e retornar se posicionou
		
		for(int i=0; i< meuTabuleiro.length; i++)
			  for (int j=0; j< meuTabuleiro[i].length; j++)
				  tabuleiroAux[i][j] = meuTabuleiro[i][j];
		
		for (int i = 0; i < 5; i++ ) {
			for (int j = 1; j >= 0; j-- ) {
				if (pontosDaArma[j][i] == 1){
					if (tabuleiroAux[x + i][y + j - 1] != 0 || tabuleiroAux[x + i + 1][y + j - 1] != 0 ||
						tabuleiroAux[x + i][y + j] != 0)
					{
						return false;
					}
					tabuleiroAux[x + i][y + j - 1] = pontosDaArma[j][i];
				}
			}
		}
		meuTabuleiro = tabuleiroAux;
		return true;
	}
	
	public void atualizaArmasPosicionadas (int pos, boolean estado)
	{
		armasPosicionadas[pos] = estado;
		for (boolean b: armasPosicionadas)
			if (!b)
				return;
		this.notifyObservers ();
	}
	
	public Arma getArmaSelecionada() {
		for (Arma a: armas) {
			if (a.getSelecionada() == true) return a;
		}
		return null;
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
    public void notifyObservers ()
    {
    	// Chama o método de atualização de todos os observers disponíveis
    	for (ObservadorIF ob : observers)
        {
        	System.out.println ("Notificando observers!");
        	ob.update (true);
         }
    }
}