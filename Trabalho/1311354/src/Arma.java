public class Arma
{
	private int[][] pontos = new int[2][5];
	private TipoDeArma tipo;
	
	public Arma (TipoDeArma tipo)
	{
		this.tipo = tipo;
		
		switch (tipo)
		{
			case Couracado:
				pontos[1][0] = 1;
				pontos[1][1] = 1;
				pontos[1][2] = 1;
				pontos[1][3] = 1;
				pontos[1][4] = 1;
				break;
			case Submarino:
				pontos[1][0] = 1;
				break;
			case Cruzador:
				pontos[1][0] = 1;
				pontos[1][1] = 1;
				pontos[1][2] = 1;
				pontos[1][3] = 1;
				break;
			case Hidroaviao:
				pontos[1][0] = 1;
				pontos[0][1] = 1;
				pontos[1][2] = 1;
				break;
			case Destroyer:
				pontos[1][0] = 1;
				pontos[1][1] = 1;
				break;
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
}