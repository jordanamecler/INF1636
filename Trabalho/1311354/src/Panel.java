import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Panel extends JPanel
{
	public static final int TXT_X = 300;
	public static final int TXT_Y = 300;
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2d = (Graphics2D) g;
		
		setSize (TXT_X, TXT_Y);
		setBackground (Color.white);
		
		// Desenha retângulo
		double leftX = 0;
		double topY = 0;
		double larg = TXT_X;
		double alt = TXT_Y;
		Rectangle2D rt = new Rectangle2D.Double (leftX, topY, larg, alt);
		g2d.draw (rt);
		// Desenha a elipse interna ao retângulo
		Ellipse2D e = new Ellipse2D.Double ();
		e.setFrame (rt);
		g2d.draw (e);
	}
}