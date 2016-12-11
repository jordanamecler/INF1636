package Model;

public class InformacoesGlobais
{
	private static InformacoesGlobais informacoesGlobais = null;
	private Jogador jogador1 = new Jogador ();
	private Jogador jogador2 = new Jogador ();
	private Jogador jogadorCorrente;
	private boolean existeVencedor = false;
	
	private InformacoesGlobais ()
	{
		
	}
	
	public void setJogadores(Jogador jog1, Jogador jog2) {
		this.jogador1 = jog1;
		this.jogador2 = jog2;
	}

	public Jogador getJogadorNaoCorrente ()
	{
		if (jogadorCorrente == jogador1)
			return jogador2;
		return jogador1;
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