package Controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.InformacoesGlobais;
import Model.Jogador;
import View.Mapa;

public class Jogo extends JFrame
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	
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
	//	c.add (panel);

		
		setBounds (0, 0, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		
		setVisible (true);
	}
}