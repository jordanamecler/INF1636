package Others;

import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

import Controller.PosicionarNavios;
import Model.Arma;
import Model.InformacoesGlobais;
import Model.Jogador;
import View.ArmaView;
import View.Mapa;

public class TratadorMouse implements MouseListener
{
	private Component c;
	
	public TratadorMouse (Component x)
	{
		c = x;
	}
	
	public void mousePressed (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	
	public void mouseClicked (MouseEvent e)
	{
		// escolheu alguma arma
		if (c.getName () == "cv" || c.getName ().contains ("sv") || c.getName ().contains ("dv") || c.getName ().contains ("crv") || c.getName ().contains ("hv"))
		{
			System.out.println(c.getName());
			ArmaView a = (ArmaView) c;
			PosicionarNavios frame = (PosicionarNavios) SwingUtilities.getRoot(c);
			int jog = frame.jog;
			System.out.println(jog);
			if (a.estaDisponivel () && !a.estaEmTransicao () && a.clicouNaArma (e.getX (), e.getY ()))
			{	
				a.setEmTransicao (true);
				InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais();
				Jogador j = inf.getJogador(jog);
				j.atualizaArmasPosicionadas (0, true);
			}
			else if (a.estaEmTransicao()) 
			{
				a.setEmTransicao(false);
				InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais();
				Jogador j1 = inf.getJogador(1);
				j1.atualizaArmasPosicionadas (0, false);
			}
		}
		else if (c.getName ().contains ("mapa"))
		{
			InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
			Jogador jog;
			
			if (c.getName ().contains ("1"))
				jog = inf.getJogador (1);
			else
				jog = inf.getJogador (2);
			
			Arma armaSelecionada = jog.getArmaSelecionada();
			if (armaSelecionada != null) {
				Mapa m = (Mapa) c;
				Point p = m.getPosicaoNoMapa(e.getX(), e.getY());
				Boolean conseguiuPosicionar = jog.posicionarArmaNoTabuleiro(p.x, p.y, armaSelecionada);
				m.marcaMapa(jog.getMeuTabuleiro());
				System.out.println("Conseguiu posicionar arma:");
				System.out.println(conseguiuPosicionar);
			}
			
			
//			jog.marcarMeuTabuleiro(p.x, p.y);
//			m.pintaRetanguloNaPosicao(p, Color.red);
//			System.out.println ("Posicao do mouse: (" + e.getX () + "," + e.getY () + ")");
		}
	}
}