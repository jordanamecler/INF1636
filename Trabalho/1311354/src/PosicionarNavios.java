import javax.swing.*;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PosicionarNavios extends JFrame
{
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	
	public PosicionarNavios (int num)
	{
		setTitle ("Batalha Naval");
		
		JLabel label = new JLabel ();
		label.setText (InformacoesGlobais.getInformacoesGlobais ().getJogador (num));
		
		Container c = getContentPane ();
		
		Toolkit tk = Toolkit.getDefaultToolkit ();
		Dimension screenSize = tk.getScreenSize ();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_DEFAULT/2;
		int y = sa/2 - ALT_DEFAULT/2;
	
		JButton terminei = new JButton ("Terminei");
		terminei.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if (num == 1)
				{
					new PosicionarNavios (2);
					dispose ();
				}
			}
		});
		
		label.setBounds (410, 500, 100, 25);
		label.setSize (label.getPreferredSize ());
		label.setHorizontalAlignment (JLabel.CENTER);
		terminei.setBounds (410, 530, 100, 25);
		terminei.setSize (terminei.getPreferredSize ());
		terminei.setHorizontalAlignment (JButton.CENTER);
		
		add (label);
		add (terminei);
		c.add (new Mapa ());
		
		setBounds (x, y, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		
		setVisible (true);
	}
}