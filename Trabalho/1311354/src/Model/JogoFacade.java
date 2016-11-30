package Model;

public class JogoFacade
{	
	public void iniciaJogo ()
	{
		
	}
	
	public void trocaJogador ()
	{
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		
		if (inf.getJogadorCorrente () == inf.getJogador (1))
			inf.setJogadorCorrente (inf.getJogador (2));
		else
			inf.setJogadorCorrente (inf.getJogador (1));
	}
}