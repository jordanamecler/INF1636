package View;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controller.JogoController;
import Others.ObservadoIF;
import Others.ObservadorIF;
import Others.TratadorMouseJogo;

public class Jogo extends JFrame implements ObservadorIF, ObservadoIF
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	private JButton comecar;
	JLabel nomeJogadorLabel;
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	private int[][] meuTabuleiro;
	private int[][] tabuleiroInimigo;

	public Jogo (ObservadorIF observador, String nome, int[][] tab, int[][] tabInimigo)
	{
		setTitle ("Batalha Naval");
		
		Container c = getContentPane ();
		c.setLayout (null);
		
		JogoController controller = (JogoController) observador;
		controller.registerObserver (this);
		
		meuTabuleiro = tab;
		tabuleiroInimigo = tabInimigo;
		
		nomeJogadorLabel = new JLabel ();
		nomeJogadorLabel.setText (nome);
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
		label.setText ("Visão bloqueada, " + nome + " deve clicar no botão para desbloquear sua visão");
		
		comecar = new JButton ("Começar Jogo!");
		comecar.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if (comecar.getText () == "Trocar jogador")
					notifyObservers ("trocou_jogador", null);
				
				comecar.setText ("Trocar jogador");
				comecar.setEnabled (false);
				mapa1.setBloqueado (false);
				mapa2.setBloqueado (false);
				mapa1.marcaMapa (tabuleiroInimigo);
				mapa2.marcaMapa (meuTabuleiro);
				mapa1.registerObserver (observador);
				JogoController controller = (JogoController) observador;
				controller.registerObserver (mapa1);
				
				repaint ();
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
			case "troca_de_jogador":
				nomeJogadorLabel.setText ((String) ((Object[]) obj)[0]);
				meuTabuleiro = (int[][]) ((Object[]) obj)[1];
				tabuleiroInimigo = (int[][]) ((Object[]) obj)[2];
				break;
			case "atirou_tres_vezes":
				comecar.setEnabled (true);
				break;
			case "jogador_ganhou":
				JOptionPane.showMessageDialog (null, (String) obj + " venceu a partida!");
				dispose ();
		}
	}
	
	public void registerObserver (ObservadorIF observer)
	{
		observers.add (observer);
	}
	
    public void removeObserver (ObservadorIF observer)
    {
    	observers.remove (observer);
    }
    
    public void notifyObservers (String mensagem, Object obj)
    {
    	ListIterator <ObservadorIF> li = observers.listIterator ();
		
		while (li.hasNext ())
		{
			ObservadorIF ob = (ObservadorIF) li.next ();
			ob.update (mensagem, null);
			//nao remove
		}
    }
}