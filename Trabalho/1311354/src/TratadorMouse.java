import java.awt.*;
import java.awt.event.*;

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
		if (c.getName () == "cv" || c.getName ().contains ("sv"))
		{
			ArmaView a = (ArmaView) c;
			if (a.estaDisponivel () && !a.estaEmTransicao () && a.clicouNaArma (e.getX (), e.getY ()))
				a.setEmTransicao ();
		}
		else if (c.getName ().contains ("mapa"))
		{
			InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
			Jogador jog;
			
			if (c.getName ().contains ("1"))
				jog = inf.getJogador (1);
			else
				jog = inf.getJogador (2);
			
			Mapa m = (Mapa) c;
			Point p = m.getPosicaoNoMapa(e.getX(), e.getY());
			jog.marcarMeuTabuleiro(p.x, p.y);
			m.pintaRetanguloNaPosicao(p, Color.red);
			System.out.println ("Posicao do mouse: (" + e.getX () + "," + e.getY () + ")");
		}
	}
}