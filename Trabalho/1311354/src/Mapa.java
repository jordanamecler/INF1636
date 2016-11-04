import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.geom.*;

public class Mapa extends JPanel
{
	private static final int TXT_X = 300;
	private static final int TXT_Y = 300;
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2d = (Graphics2D) g;
		
		setBounds (500, 125, TXT_X, TXT_Y);
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
				g2d.draw (rt);
			}
			
		}
	}
}