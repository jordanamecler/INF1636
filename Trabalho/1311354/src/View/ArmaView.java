package View;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

import Model.Arma;

public class ArmaView extends JPanel
{	
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private Arma arma;
	private boolean disponivel = true;
	private boolean emTransicao = false;
	
	public ArmaView (Arma a)
	{
		this.arma = a;
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Desenha arma
		double leftX = 0;
		double topY = 0;
		double larg = 15;
		double alt = 15;
		
		int[][] pontos = arma.getPontos ();
		Color color = Color.white;
		
		if (disponivel && !emTransicao)
		{
			switch (arma.getTipoDeArma ())
			{
				case Couracado:
					color = Color.orange;
					break;
				case Submarino:
					color = Color.blue;
					break;
				case Cruzador:
					color = Color.green;
					break;
				case Hidroaviao:
					color = Color.CYAN;
					break;
				case Destroyer:
					color = Color.black;
					break;
			}
		}
		else if (emTransicao)
		{
			switch (arma.getTipoDeArma ())
			{
				case Couracado:
					color = new Color (255, 221, 181);
					break;
				case Submarino:
					color = Color.blue;
					break;
				case Cruzador:
					color = Color.green;
					break;
				case Hidroaviao:
					color = Color.CYAN;
					break;
				case Destroyer:
					color = Color.black;
					break;
			}
		}
		else
		{
			switch (arma.getTipoDeArma ())
			{
				case Couracado:
					color = Color.orange;
					break;
				case Submarino:
					color = Color.blue;
					break;
				case Cruzador:
					color = Color.green;
					break;
				case Hidroaviao:
					color = Color.CYAN;
					break;
				case Destroyer:
					color = Color.black;
					break;
			}
		}
		
		for (int i = 0; i < pontos.length; i++)
		{	
			leftX = 0;
			topY = i*alt;
			
			for (int j = 0; j < pontos[0].length; j++)
			{
				leftX = j*larg;
				
				if (pontos[i][j] == 1)
				{
					Rectangle2D rt = new Rectangle2D.Double (leftX, topY, larg, alt);
					g2d.setColor (color);
					g2d.fill (rt);
					g2d.draw (rt);
				}
			}
		}
	}
	
	public void setEmTransicao (Boolean b)
	{
		emTransicao = b;
		repaint ();
	}
	
	public void setDisponivel (Boolean b)
	{
		disponivel = b;
		repaint ();
	}
	
	public void setIndisponivel ()
	{
		disponivel = false;
		emTransicao = false;
		repaint ();
	}
	
	public boolean estaDisponivel ()
	{
		return disponivel;
	}
	
	public boolean estaEmTransicao ()
	{
		return emTransicao;
	}
	
	public boolean clicouNaArma (Integer x, Integer y)
	{
		int xPoint = (int) (x.doubleValue () * 15.0 / 225.0f);
		int yPoint = (int) (y.doubleValue () * 15.0 / 225.0f);
		int[][] pts = arma.getPontos ();
		if (pts[yPoint][xPoint] == 1)
			return true;
		else
			return false;
	}
}