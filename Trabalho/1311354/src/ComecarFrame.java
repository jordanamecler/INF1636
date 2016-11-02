import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComecarFrame extends JFrame
{
	public final int LARG_DEFAULT = 410;
	public final int ALT_DEFAULT = 300;
	
	public ComecarFrame ()
	{
		setLayout (null);
		JLabel jogador1 = new JLabel ("Jogador 1");
		JLabel jogador2 = new JLabel ("Jogador 2");
		setTitle ("Jogadores");
		Container c = getContentPane ();
		JTextField jog1 = new JTextField ();
		JTextField jog2 = new JTextField ();
		JButton comecar = new JButton ("Começar");
		comecar.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if (!jog1.getText ().isEmpty () && !jog2.getText ().isEmpty ())
				{
					dispose ();
				}
			}
		});
		
		jogador1.setBounds (80, 53, 65, 25);
		jogador2.setBounds (80, 83, 65, 25);
		jog1.setBounds (150 ,50, 150, 25);
		jog2.setBounds (150 ,80, 150, 25);
		comecar.setBounds (160, 150, 100, 25);
		c.add (jogador1);
		c.add (jogador2);
		c.add (jog1);
		c.add (jog2);
		c.add (comecar);
		setSize (LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
	}
}