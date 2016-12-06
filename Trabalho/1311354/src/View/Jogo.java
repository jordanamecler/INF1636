package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.JogoController;
import Model.InformacoesGlobais;
import Model.Jogador;
import Model.JogoFacade;
import Others.ObservadorIF;
import Others.TratadorMouseJogo;

public class Jogo extends JFrame implements ObservadorIF
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	private JogoFacade facade = null;
	private JButton comecar;

	public Jogo (ObservadorIF observador)
	{
		setTitle ("Batalha Naval");

		JogoController controller = (JogoController) observador;
		controller.registerObserver (this);
		
		Jogador j1 = InformacoesGlobais.getInformacoesGlobais ().getJogador (1);
		
		Container c = getContentPane ();
		c.setLayout (null);
		
		JLabel nomeJogadorLabel = new JLabel ();
		nomeJogadorLabel.setText (j1.getNome ());
		nomeJogadorLabel.setBounds (300, 40, 200,40);
		nomeJogadorLabel.setHorizontalAlignment (JLabel.CENTER);
		c.add (nomeJogadorLabel);
		
		Mapa mapa1 = new Mapa (100, 125);
		mapa1.setName ("mapa_inimigo");
		mapa1.setLayout (null);
		mapa1.addMouseListener (new TratadorMouseJogo (mapa1));
		
		Mapa mapa2 = new Mapa (500, 125);
		mapa2.setLayout (null);
		
		mapa1.setSize (300, 300);
		mapa2.setSize (300, 300);
		
		mapa1.setBloqueado (true);
		mapa2.setBloqueado (true);
		
		JLabel meuLabel = new JLabel ();
		meuLabel.setText ("Meu mapa");
		meuLabel.setBounds (540, 70,200,40);
		meuLabel.setHorizontalAlignment (JLabel.CENTER);
		c.add (meuLabel);
		
		JLabel inimigoLabel = new JLabel ();
		inimigoLabel.setText ("Mapa do inimigo");
		inimigoLabel.setBounds (140, 70,200,40);
		inimigoLabel.setHorizontalAlignment (JLabel.CENTER);
		c.add (inimigoLabel);
		
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
			
			JLabel legendaY1 = new JLabel ();
			legendaY1.setText (Character.toString ((char) (i + 65)));
			legendaY1.setBounds (475, 125 + 20 * i, 20, 25);
			legendaY1.setHorizontalAlignment (JLabel.CENTER);
			
			JLabel legendaY2 = new JLabel ();
			legendaY2.setText (Character.toString ((char) (i + 65)));
			legendaY2.setBounds (75, 125 + 20 * i, 20, 25);
			legendaY2.setHorizontalAlignment (JLabel.CENTER);
			
			c.add (legendaX1);
			c.add (legendaX2);
			c.add (legendaY1);
			c.add (legendaY2);
		}

		JLabel label = new JLabel ();
		label.setText ("Visão bloqueada, " + j1.getNome () + " deve clicar no botão para desbloquear sua visão");
		
		comecar = new JButton ("Começar Jogo!");
		comecar.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				comecar.setEnabled (false);
				mapa1.setBloqueado (false);
				mapa2.setBloqueado (false);
				mapa1.marcaMapa (j1.getTabuleiroInimigo ());
				mapa2.marcaMapa (j1.getMeuTabuleiro ());
				mapa1.registerObserver (observador);
				JogoController controller = (JogoController) observador;
				controller.registerObserver (mapa1);
			}
		});
		
		comecar.setBounds (390, 530, 100, 25);
		comecar.setSize (comecar.getPreferredSize ());
		comecar.setHorizontalAlignment (JButton.CENTER);

		label.setBounds (200, 490, 400, 25);
		label.setSize (label.getPreferredSize ());
		
		c.add (mapa1);
		pack ();
		c.add (mapa2);
		c.add (comecar);
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
	
	public void update (String caso, Object obj)
	{
		switch (caso)
		{
			case "atirou_tres_vezes":
				comecar.setEnabled (true);
				break;
		}
	}
}