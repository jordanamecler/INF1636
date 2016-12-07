package Model;

import java.awt.Point;

public class JogoFacade
{	
	public void iniciaJogo ()
	{
		
	}
	
	public static void trocaJogador ()
	{
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		
		if (inf.getJogadorCorrente () == inf.getJogador (1))
			inf.setJogadorCorrente (inf.getJogador (2));
		else
			inf.setJogadorCorrente (inf.getJogador (1));
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
					inf.setJogadorVencedor (inf.getJogadorCorrente ());
					System.out.println ("Jogador " + inf.getJogadorCorrente ().getNome () + " venceu!");
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
					inf.setJogadorVencedor (inf.getJogadorCorrente ());
					System.out.println ("Jogador " + inf.getJogadorCorrente ().getNome () + " venceu!");
				}
			}
			else
				return false;
		}
		
		inf.getJogadorCorrente ().marcarTabuleiroInimigo (ponto.x, ponto.y, acertou);
		return true;
	}
}