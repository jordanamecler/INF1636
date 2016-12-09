package View;

import Model.CoresMapa;
import Others.ObservadoIF;
import Others.ObservadorIF;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Mapa extends JPanel implements ObservadoIF, ObservadorIF
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int TXT_X = 300;
	private final int TXT_Y = 300;
	private int distanciaX = 500;
	private int distanciaY = 125;
	private int[][] retangulos = new int[15][15];
	private boolean bloqueado = false;
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	
	public Mapa ()
	{
		
	}
	
	public Mapa (int x, int y)
	{
		distanciaX = x;
		distanciaY = y;
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2d = (Graphics2D) g;
		
		setBounds (distanciaX, distanciaY, TXT_X, TXT_Y);
		setBackground (Color.white);
		
		// Desenha retângulo
		double leftX = 0;
		double topY = 0;
		double larg = TXT_X/15;
		double alt = TXT_Y/15;
		
		for (int i = 0; i < 15; i++)
		{
			leftX = 0;
			topY = i*alt;
			
			for (int j = 0; j < 15; j++)
			{
				leftX = j*larg;
				Rectangle2D rt = new Rectangle2D.Double (leftX, topY, larg, alt);
				
				if (bloqueado)
					g2d.setColor (CoresMapa.Bloqueado.getColor ());
				else {
					switch (retangulos[j][i])
					{
						case 0:
							g2d.setColor (CoresMapa.Vazio.getColor ());
							break;
						case 1:
							g2d.setColor (CoresMapa.Couracado.getColor ());
							break;
						case 2:
							g2d.setColor (CoresMapa.Submarino.getColor ());
							break;
						case 3:
							g2d.setColor (CoresMapa.Cruzador.getColor ());
							break;
						case 4:
							g2d.setColor (CoresMapa.Hidroaviao.getColor ());
							break;
						case 5:
							g2d.setColor (CoresMapa.Destroyer.getColor ());
							break;
						case 6:
							g2d.setColor (CoresMapa.Destruida.getColor ());
							break;	
						case 7:
							g2d.setColor (CoresMapa.VazioAtingido.getColor ());
							
					}
				}
				g2d.fill (rt);
				g2d.setColor (Color.black);
				g2d.draw (rt);
			}
		}
	}
	
	public void setBloqueado (boolean bloq)
	{
		bloqueado = bloq;
		repaint ();
	}
	
	public boolean getBloquado ()
	{
		return bloqueado;
	}
	
	public Point getPosicaoNoMapa (Integer x, Integer y)
	{
		Point p = new Point ();
		
		p.x = (int) (int) (x.doubleValue () * 15 / 300.0f);
		p.y = (int) (int) (y.doubleValue () * 15 / 300.0f);
		
		return p;
	}
	
	public void pintaRetanguloNaPosicao (Point p, Color c)
	{	
		retangulos[p.x][p.y] = 1;
		repaint ();
	}
	
	public void marcaMapa (int[][] mapa)
	{
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				retangulos[j][i] = mapa[j][i];
			}
		}
		repaint ();
	}

	@Override
	public void registerObserver (ObservadorIF observer)
	{
        observers.add (observer);	
	}

	@Override
	public void removeObserver (ObservadorIF observer)
	{
		observers.remove (observer);	
	}

	@Override
	public void notifyObservers (String mensagem, Object obj)
	{
		ListIterator <ObservadorIF> li = observers.listIterator ();
		
		while (li.hasNext ())
		{
			ObservadorIF ob = (ObservadorIF) li.next ();
			ob.update (mensagem, obj);
			// nao remove observer nesse caso
		}	
	}

	@Override
	public void update (String caso, Object obj)
	{
		switch (caso)
		{
			case "marcar_mapa":
				int[][] mapa = (int [][]) obj;
				marcaMapa (mapa);
				break;
		}
	}
}