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

public class TratadorMouseJogo implements MouseListener
{
	private Component c;
	
	public TratadorMouseJogo (Component x)
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
			if (c.getName ().contains ("mapa"))
			{	
				
			}
			else
			{	
				
			}
		}
		
	}
}