package Model;

public class InformacoesGlobais
{
	private static InformacoesGlobais informacoesGlobais = null;
	private Jogador jogador1 = new Jogador ();
	private Jogador jogador2 = new Jogador ();
	
	
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
	
//	public void setJogador (int num, String nome)
//	{
//		if (num == 1)
//			jogador1 = nome;
//		else if (num ==2 )
//			jogador2 = nome;
//	}
}