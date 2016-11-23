import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jogo extends JFrame
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	
	public Jogo ()
	{
		setTitle ("Batalha Naval");

		Container c = getContentPane ();
		Mapa mapa1 = new Mapa ();
		Mapa mapa2 = new Mapa (100, 125);
		
		mapa1.setBloqueado (true);
		mapa2.setBloqueado (true);
		
		JPanel panel = new JPanel ();
		panel.setLayout (new BoxLayout (panel, BoxLayout.X_AXIS));
		
		JButton terminei = new JButton ("Começar Jogo!");
		terminei.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
			}
		});
		
		terminei.setBounds (390, 530, 100, 25);
		terminei.setSize (terminei.getPreferredSize ());
		terminei.setHorizontalAlignment (JButton.CENTER);

		c.add (terminei);
		panel.add (mapa1);
		panel.add (mapa2);
		c.add (panel);

		
		setBounds (0, 0, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		
		setVisible (true);
	}
}