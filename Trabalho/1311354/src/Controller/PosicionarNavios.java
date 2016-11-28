package Controller;

import javax.swing.*;

import Model.Arma;
import Model.InformacoesGlobais;
import Model.Jogador;
import Model.TipoDeArma;
import Others.ObservadorIF;
import Others.TratadorMouse;
import View.ArmaView;
import View.Mapa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PosicionarNavios extends JFrame implements ObservadorIF
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	private JButton terminei;
	public int jog;

	public PosicionarNavios (int numJogador)
	{
		setTitle ("Batalha Naval");
		Container c = getContentPane ();
		
		jog = numJogador;
		
		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		Jogador jog = inf.getJogador (numJogador);
		
		JLabel label = new JLabel ();
		label.setText (jog.getNome ());
		
		// Mapa para escolher as posicoes das armas
		Mapa mapa = new Mapa ();
	
		// Armas
		
		// 1x couracado
		Arma couracado = new Arma (TipoDeArma.Couracado);
		jog.armas.add(couracado);
		
		ArmaView cv = new ArmaView (couracado);
		cv.setBounds (50, 100, 15 * 5, 15 * 2);
		cv.setName ("cv");
		cv.addMouseListener (new TratadorMouse (cv));
		c.add (cv);
		
		// 4x submarinos
		for (int i = 0; i < 4; i++)
		{
			Arma submarino = new Arma (TipoDeArma.Submarino);
			jog.armas.add(submarino);
			ArmaView sv = new ArmaView (submarino);
			sv.setBounds (50 + i * 25  ,180 , 15 , 15 * 2);
			c.add (sv);
			sv.setName ("sv" + (i+1));
			sv.addMouseListener (new TratadorMouse (sv));
		}
		
		// 2x cruzadores
		for (int i = 0; i < 2; i++)
		{
			Arma cruzador = new Arma (TipoDeArma.Cruzador);
			jog.armas.add(cruzador);
			ArmaView crv = new ArmaView (cruzador);
			crv.setBounds (50 + i * 25 * 4,260, 15 * 4, 15 * 2);
			c.add (crv);
			crv.setName ("crv" + (i+1));
			crv.addMouseListener (new TratadorMouse (crv));
		}
		
		// 5x hidroavioes
		for (int i = 0; i < 5; i++)
		{
			Arma hidro = new Arma (TipoDeArma.Hidroaviao);
			jog.armas.add(hidro);
			ArmaView hv = new ArmaView (hidro);
			hv.setBounds (50 + i * 25 * 3,340, 15 * 3, 15 * 2);
			c.add (hv);
			hv.setName ("hv" + (i+1));
			hv.addMouseListener (new TratadorMouse (hv));
		}
		
		// 3x destroyers
		for (int i = 0; i < 3; i++)
		{
			Arma des = new Arma (TipoDeArma.Destroyer);
			jog.armas.add(des);
			ArmaView dv = new ArmaView (des);
			dv.setBounds (50 + i * 25 * 2,420, 15 * 2, 15 * 2);
			c.add (dv);
			dv.setName ("dv" + (i+1));
			dv.addMouseListener (new TratadorMouse (dv));
		}	
		
		mapa.setName ("mapa" + numJogador);
		mapa.addMouseListener (new TratadorMouse (mapa));
		
		terminei = new JButton ("Terminei");
		//terminei.setEnabled (false);
		terminei.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if (numJogador == 1)
				{
					new PosicionarNavios (2);
					dispose ();
				}
				else
				{
					new Jogo ();
					dispose ();
				}
			}
		});
		
		label.setBounds (410, 500, 100, 25);
		label.setSize (label.getPreferredSize ());
		label.setHorizontalAlignment (JLabel.CENTER);
		terminei.setBounds (390, 530, 100, 25);
		terminei.setSize (terminei.getPreferredSize ());
		terminei.setHorizontalAlignment (JButton.CENTER);
		
		c.add (label);
		c.add (terminei);
		c.add (mapa);	
		
		Toolkit tk = Toolkit.getDefaultToolkit ();
		Dimension screenSize = tk.getScreenSize ();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_DEFAULT/2;
		int y = sa/2 - ALT_DEFAULT/2;
		
		setBounds (x, y, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		
		InformacoesGlobais.getInformacoesGlobais ().getJogador (numJogador).registerObserver (this);
		
		setVisible (true);
	}
	
	@Override
	public void update (Object obj)
	{
		if (((boolean) obj))
		{
			terminei.setEnabled (true);
			InformacoesGlobais.getInformacoesGlobais ().getJogador (jog).removeObserver (this);
		}
	}
}