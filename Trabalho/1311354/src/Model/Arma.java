package Model;

import Model.Direcao;
import Model.EstadoPosicionamento;
import java.awt.Point;

public class Arma
{
	private int[][] pontos = new int[2][5]; // 0 = vazio, 1 = preenche
	private int[][] estado = new int[2][5]; // 0 = vazio, 1 = ok, 2 = destruido
	private TipoDeArma tipo;
	private Point posicao;
	private Point primeiroPontoNoMapa = new Point ();
	private Direcao direcao = Direcao.Padrao;
	private int largura;
	private int altura;
	private EstadoPosicionamento estadoPosicionamento = EstadoPosicionamento.Disponivel;
	
	public Arma (TipoDeArma tipo)
	{
		this.tipo = tipo;
		
		switch (tipo)
		{
			case Vazio:
				break;
			case Couracado:
				pontos[1][0] = 1;
				pontos[1][1] = 1;
				pontos[1][2] = 1;
				pontos[1][3] = 1;
				pontos[1][4] = 1;
				largura = 5;
				altura = 1;
				break;
			case Submarino:
				pontos[1][0] = 1;
				largura = 1;
				altura = 1;
				break;
			case Cruzador:
				pontos[1][0] = 1;
				pontos[1][1] = 1;
				pontos[1][2] = 1;
				pontos[1][3] = 1;
				largura = 4;
				altura = 1;
				break;
			case Hidroaviao:
				pontos[1][0] = 1;
				pontos[0][1] = 1;
				pontos[1][2] = 1;
				largura = 3;
				altura = 2;
				break;
			case Destroyer:
				pontos[1][0] = 1;
				pontos[1][1] = 1;
				largura = 2;
				altura = 1;
				break;
			case Destruida:
				break;
		}
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				estado[j][i] = pontos[j][i];
			}
		}
	}
	
	public TipoDeArma getTipoDeArma ()
	{
		return tipo;
	}
	
	public int[][] getPontos ()
	{
		return pontos;
	}
	
	public int[][] getEstado ()
	{
		return estado;
	}
	
	public void setEstadoDestruido (int x, int y)
	{
		estado[x][y] = 2;
	}

	public Point getPosicao ()
	{
		return posicao;
	}

	public void setPosicao (Point posicao)
	{
		this.posicao = posicao;
	}
	
	public Point getPrimeiroPontoNoMapa ()
	{
		return primeiroPontoNoMapa;
	}
	
	public void setPrimeiroPontoNoMapa (int x, int y)
	{
		primeiroPontoNoMapa.x = x;
		primeiroPontoNoMapa.y = y;
	}
	
	public Direcao getDirecao ()
	{
		return direcao;
	}
	
	public void giraDirecao ()
	{
		switch (direcao)
		{
			case Padrao:
				direcao = Direcao.NoventaGraus;
				break;
			case NoventaGraus:
				direcao = Direcao.CentoEOitentaGraus;
				break;
			case CentoEOitentaGraus:
				direcao = Direcao.DuzentosESetentaGraus;
				break;
			case DuzentosESetentaGraus:
				direcao = Direcao.Padrao;
				break;
		}
	}
	
	public int getLargura ()
	{
		return largura;
	}
	
	public int getAltura ()
	{
		return altura;
	}

	public EstadoPosicionamento getEstadoPosicionamento ()
	{
		return estadoPosicionamento;
	}

	public void setEstadoPosicionamento (EstadoPosicionamento estadoPosicionamento)
	{
		this.estadoPosicionamento = estadoPosicionamento;
	}
}