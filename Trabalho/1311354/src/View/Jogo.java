package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.InformacoesGlobais;
import Model.Jogador;
import Model.JogoFacade;

public class Jogo extends JFrame
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	JogoFacade facade = null;

	public Jogo ()
	{
		setTitle ("Batalha Naval");

		Jogador j1 = InformacoesGlobais.getInformacoesGlobais().getJogador (1);
		
		Container c = getContentPane ();
		c.setLayout (null);
		
		Mapa mapa1 = new Mapa (500, 125);
		mapa1.setLayout (null);
		Mapa mapa2 = new Mapa (100, 125);
		mapa2.setLayout (null);
		
		mapa1.setSize (300, 300);
		mapa2.setSize (300, 300);
		
		mapa1.setBloqueado (true);
		mapa2.setBloqueado (true);
		
		for (int i = 0; i < 15; i++)
		{
			JLabel legendaX1 = new JLabel ();
			legendaX1.setText (Integer.toString (i + 1));
			legendaX1.setBounds (500 + 20 * i, 100, 20, 25);
			legendaX1.setHorizontalAlignment (JLabel.CENTER);
			
			JLabel legendaX2 = new JLabel ();
			legendaX2.setText (Integer.toString (i + 1));
			legendaX2.setBounds (100 + 20 * i, 100, 20, 25);
			legendaX2.setHorizontalAlignment (JLabel.CENTER);
			
			c.add (legendaX1);
			c.add (legendaX2);
		}
		
		for (int i = 0; i < 15; i++)
		{
			JLabel legendaY1 = new JLabel ();
			legendaY1.setText (Character.toString ((char) (i + 65)));
			legendaY1.setBounds (475, 125 + 20 * i, 20, 25);
			legendaY1.setHorizontalAlignment (JLabel.CENTER);
			
			JLabel legendaY2 = new JLabel ();
			legendaY2.setText (Character.toString ((char) (i + 65)));
			legendaY2.setBounds (75, 125 + 20 * i, 20, 25);
			legendaY2.setHorizontalAlignment (JLabel.CENTER);
			
			c.add (legendaY2);
			c.add (legendaY1);
		}

		JLabel label = new JLabel ();
		label.setText ("Visão bloqueada, " + j1.getNome () + " deve clicar no botão para desbloquear sua visão");
		
		JButton terminei = new JButton ("Começar Jogo!");
		terminei.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				
			}
		});
		
		terminei.setBounds (390, 530, 100, 25);
		terminei.setSize (terminei.getPreferredSize ());
		terminei.setHorizontalAlignment (JButton.CENTER);

		label.setBounds (200, 490, 400, 25);
		label.setSize (label.getPreferredSize ());
		
		c.add (mapa1);
		pack ();
		c.add (mapa2);
		c.add (terminei);
		c.add (label);

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