package Controller;

import javax.swing.*;

import Model.InformacoesGlobais;
import Model.Jogador;

import java.awt.*;
import java.awt.event.*;

public class EscolherJogadores extends JFrame
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 410;
	private final int ALT_DEFAULT = 300;
	
	public EscolherJogadores ()
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
					if (jog1.getText ().equals (jog2.getText ()))
						JOptionPane.showMessageDialog (null, "Os dois jogadores devem ter nomes diferentes");
					else
					{	InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais();
						Jogador j1 = inf.getJogador(1);
						Jogador j2 = inf.getJogador(2);
						j1.setNome(jog1.getText());
						j2.setNome(jog2.getText());
//						InformacoesGlobais.getInformacoesGlobais ().setJogador (1, jog1.getText ());
//						InformacoesGlobais.getInformacoesGlobais ().setJogador (2, jog2.getText ());
						new PosicionarNavios (1);
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
		setSize (LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
	}
}