import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PosicionarFrame extends JFrame
{
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	
	public PosicionarFrame ()
	{
		setTitle ("Batalha Naval");

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
				
			}
		});
		terminei.setBounds (410, 500, 100, 25);
		
		add (terminei);
		c.add (new Panel ());
		
		setBounds (x, y, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		
		setVisible (true);
	}
}