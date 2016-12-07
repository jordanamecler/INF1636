package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

//import Others.ObservadoIF;
import Others.ObservadorIF;

public class Vencedor extends JFrame // implements ObservadoIF
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	
	public Vencedor (String nomeVencedor, int[][] tabuleiro)
	{
		setTitle ("Batalha Naval");
		
		Container c = getContentPane ();
		c.setLayout (null);
		
		JLabel nomeVencedorLabel = new JLabel ();
		nomeVencedorLabel.setText (nomeVencedor + " venceu a partida!");
		nomeVencedorLabel.setBounds (300, 40, 200,40);
		nomeVencedorLabel.setHorizontalAlignment (JLabel.CENTER);
		c.add (nomeVencedorLabel);
		
		Mapa mapa1 = new Mapa (100, 125);
		mapa1.setLayout (null);
		mapa1.setName ("meu_mapa");
		mapa1.marcaMapa (tabuleiro);
		mapa1.setSize (300, 300);
		c.add (mapa1);
		pack ();
		
		
		Toolkit tk = Toolkit.getDefaultToolkit ();
		Dimension screenSize = tk.getScreenSize ();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_DEFAULT/2;
		int y = sa/2 - ALT_DEFAULT/2;
		
		setBounds (x, y, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
	}
}