package Model;

public class InformacoesGlobais
{
	private static InformacoesGlobais informacoesGlobais = null;
	private Jogador jogador1 = new Jogador ();
	private Jogador jogador2 = new Jogador ();
	private Jogador jogadorCorrente;
	private Jogador vencedor;
	private boolean existeVencedor = false;
	
	private InformacoesGlobais ()
	{
		
	}

	public static InformacoesGlobais getInformacoesGlobais ()
	{
		if (informacoesGlobais == null)
			informacoesGlobais = new InformacoesGlobais ();
		return informacoesGlobais;
	}
	
	public Jogador getJogador (int num)
	{
		if (num == 1)
			return jogador1;
		else
			return jogador2;
	}
	
	public Jogador getJogadorCorrente ()
	{
		return jogadorCorrente;
	}
	
	public void setJogadorCorrente (Jogador jog)
	{
		jogadorCorrente = jog;
	}
	
	public void setExisteVencedor ()
	{
		existeVencedor = true;
	}
	
	public boolean existeVencedor ()
	{
		return existeVencedor;
	}
}