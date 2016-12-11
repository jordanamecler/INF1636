package Others;

import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

import Model.Arma;
import Model.InformacoesGlobais;
import Model.Jogador;
import Model.EstadoPosicionamento;
import View.ArmaView;
import View.Mapa;
import View.PosicionarNavios;

public class TratadorMousePosicao implements MouseListener
{
	private Component c;
	
	public TratadorMousePosicao (Component x)
	{
		c = x;
	}
	
	public void mousePressed (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	
	public void mouseClicked (MouseEvent e)
	{
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		Jogador jog = inf.getJogadorCorrente ();
		if (SwingUtilities.isLeftMouseButton (e))
		{
			// escolheu alguma arma
			if (c.getName () == "cv" || c.getName ().contains ("sv") || c.getName ().contains ("dv") || c.getName ().contains ("crv") || c.getName ().contains ("hv"))
			{
				
				ArmaView a = (ArmaView) c;				
				if (jog.podeSelecionarArma () &&
					a.getArma ().getEstadoPosicionamento () == EstadoPosicionamento.Disponivel &&
					a.clicouNaArma (e.getX (), e.getY ())) {
					a.getArma ().setEstadoPosicionamento (EstadoPosicionamento.EmTransicao);
				}
				else if (a.getArma ().getEstadoPosicionamento () == EstadoPosicionamento.EmTransicao)
				{
						 a.getArma ().setEstadoPosicionamento (EstadoPosicionamento.Disponivel);
				}
				else if (a.getArma ().getEstadoPosicionamento () == EstadoPosicionamento.Girando)
				{
					PosicionarNavios frame = (PosicionarNavios) SwingUtilities.getRoot (c);
					frame.getMapa ().marcaMapa (jog.getMeuTabuleiro ());
					a.getArma ().setEstadoPosicionamento (EstadoPosicionamento.Disponivel);
				}
				
				a.repaint ();
			}
			else if (c.getName ().contains ("mapa"))
			{	
				if (c.getName ().contains ("1"))
					jog = inf.getJogador (1);
				else
					jog = inf.getJogador (2);
				
				Arma armaSelecionada = jog.getArmaEmEstado (EstadoPosicionamento.EmTransicao);
				
				if (armaSelecionada != null)
				{
					Mapa m = (Mapa) c;
					Point p = m.getPosicaoNoMapa (e.getX (), e.getY ());
					boolean conseguiuPosicionar = jog.posicionarArmaNoTabuleiro (p.x, p.y, armaSelecionada, false);
					if (conseguiuPosicionar)
					{
						m.marcaMapa (jog.getTabuleiroAux ());
						armaSelecionada.setEstadoPosicionamento (EstadoPosicionamento.Girando);
					}
				}
			}
			else
			{	
				Arma arma = jog.getArmaEmEstado (EstadoPosicionamento.EmTransicao);
				if (arma != null)
				{
					arma.setEstadoPosicionamento (EstadoPosicionamento.Disponivel);
					PosicionarNavios frame = (PosicionarNavios) c;
					frame.getMapa ().marcaMapa (jog.getMeuTabuleiro ());
					frame.repintaArmaView (arma);
				}
				arma = jog.getArmaEmEstado (EstadoPosicionamento.Girando);
				if (arma != null)
				{
					arma.setEstadoPosicionamento (EstadoPosicionamento.Disponivel);
					PosicionarNavios frame = (PosicionarNavios) c;
					frame.getMapa ().marcaMapa (jog.getMeuTabuleiro ());
					frame.repintaArmaView (arma);
				}
			}
		}
		else if (SwingUtilities.isRightMouseButton (e))
		{
			Arma arma = jog.getArmaEmEstado (EstadoPosicionamento.Girando);
			if (arma != null)
			{
				if (jog.posicionarArmaNoTabuleiro (arma.getPrimeiroPontoNoMapa ().x, arma.getPrimeiroPontoNoMapa ().y, arma, true))
				{
					PosicionarNavios frame;
					if (c instanceof PosicionarNavios)
						frame = (PosicionarNavios) c;
					else
						frame = (PosicionarNavios) SwingUtilities.getRoot (c);
					
					frame.getMapa ().marcaMapa (jog.getTabuleiroAux ());
				}
			}
		}
	}
}