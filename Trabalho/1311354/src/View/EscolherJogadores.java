package View;

import javax.swing.*;

import Others.ObservadoIF;
import Others.ObservadorIF;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class EscolherJogadores extends MenuFrame implements ObservadoIF, ObservadorIF
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 410;
	private final int ALT_DEFAULT = 300;
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	private JTextField jog1;
	private JTextField jog2;
	
	public EscolherJogadores ()
	{
		super (false, true);
		
		setLayout (null);
		JLabel jogador1 = new JLabel ("Jogador 1");
		JLabel jogador2 = new JLabel ("Jogador 2");
		setTitle ("Jogadores");
		Container c = getContentPane ();
		jog1 = new JTextField ();
		jog2 = new JTextField ();
		JButton comecar = new JButton ("Começar");
		comecar.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if (!jog1.getText ().isEmpty () && !jog2.getText ().isEmpty ())
				{
					if (jog1.getText ().equals (jog2.getText ()))
						JOptionPane.showMessageDialog (null, "Os dois jogadores devem ter nomes diferentes");
					else
					{							
						notifyObservers ("escolheu_jogadores", null);
						dispose ();
					}
				}
				else
					JOptionPane.showMessageDialog (null, "Os dois jogadores devem ter um nome");
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

	@Override
	public void registerObserver (ObservadorIF observer)
	{
		observers.add (observer);
	}

	@Override
	public void removeObserver (ObservadorIF observer) 
	{
		observers.remove (observer);
	}

	@Override
	public void notifyObservers (String mensagem, Object obj) 
	{
		ListIterator <ObservadorIF> li = observers.listIterator ();
		
		while (li.hasNext ())
		{
			ObservadorIF ob = (ObservadorIF) li.next ();
			Vector <String> vector = new Vector <String> (2);
    		vector.addElement (this.jog1.getText ());
    		vector.addElement (this.jog2.getText ());
			ob.update (mensagem, vector);
			li.remove ();
		}
	}
	
	public void update (String caso, Object obj)
	{
		if (caso == "jogo_carregado")
			dispose ();
	}
}