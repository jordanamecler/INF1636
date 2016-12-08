package View;

import javax.swing.*;

import Model.Arma;
import Model.InformacoesGlobais;
import Model.Jogador;
import Model.TipoDeArma;
import Others.ObservadorIF;
import Others.ObservadoIF;
import Others.TratadorMousePosicao;
import Others.TratadorTeclado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PosicionarNavios extends JFrame implements ObservadorIF, ObservadoIF
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	private JButton terminei;
	private Mapa mapa;
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	private List <ArmaView> armasViews = new ArrayList <ArmaView> ();
	
	public PosicionarNavios (int numJogador, String nomeJogador)
	{
		setTitle ("Batalha Naval");
		Container c = getContentPane ();
		
		this.addKeyListener (new TratadorTeclado ());
		this.addMouseListener (new TratadorMousePosicao (this));	

		InformacoesGlobais inf = InformacoesGlobais.getInformacoesGlobais ();
		Jogador jog = inf.getJogador (numJogador);
		
		JLabel label = new JLabel ();
		label.setText (nomeJogador);
		
		// Mapa para escolher as posicoes das armas
		mapa = new Mapa ();
		
		for (int i = 0; i < 15; i++)
		{
			JLabel legendaX = new JLabel ();
			legendaX.setText (Integer.toString (i + 1));
			legendaX.setBounds (500 + 20 * i, 100, 20, 25);
			legendaX.setHorizontalAlignment (JLabel.CENTER);
			
			JLabel legendaY = new JLabel ();
			legendaY.setText (Character.toString ((char) (i + 65)));
			legendaY.setBounds (475, 125 + 20 * i, 20, 25);
			legendaY.setHorizontalAlignment (JLabel.CENTER);
			
			c.add (legendaX);
			c.add (legendaY);
		}
		
		// Armas
		
		// 1x couracado
		Arma couracado = new Arma (TipoDeArma.Couracado);
		jog.getListaArmas ().add (couracado);
		
		ArmaView cv = new ArmaView (couracado);
		armasViews.add (cv);
		cv.setBounds (50, 100, 15 * 5, 15 * 2);
		cv.setName ("cv");
		cv.addMouseListener (new TratadorMousePosicao (cv));
		c.add (cv);
		
		// 4x submarinos
		for (int i = 0; i < 4; i++)
		{
			Arma submarino = new Arma (TipoDeArma.Submarino);
			jog.getListaArmas ().add (submarino);
			ArmaView sv = new ArmaView (submarino);
			armasViews.add (sv);
			sv.setBounds (50 + i * 25  ,180 , 15 , 15 * 2);
			c.add (sv);
			sv.setName ("sv" + (i+1));
			sv.addMouseListener (new TratadorMousePosicao (sv));
		}
		
		// 2x cruzadores
		for (int i = 0; i < 2; i++)
		{
			Arma cruzador = new Arma (TipoDeArma.Cruzador);
			jog.getListaArmas ().add (cruzador);
			ArmaView crv = new ArmaView (cruzador);
			armasViews.add (crv);
			crv.setBounds (50 + i * 25 * 4,260, 15 * 4, 15 * 2);
			c.add (crv);
			crv.setName ("crv" + (i+1));
			crv.addMouseListener (new TratadorMousePosicao (crv));
		}
		
		// 5x hidroavioes
		for (int i = 0; i < 5; i++)
		{
			Arma hidro = new Arma (TipoDeArma.Hidroaviao);
			jog.getListaArmas ().add (hidro);
			ArmaView hv = new ArmaView (hidro);
			armasViews.add (hv);
			hv.setBounds (50 + i * 25 * 3,340, 15 * 3, 15 * 2);
			c.add (hv);
			hv.setName ("hv" + (i+1));
			hv.addMouseListener (new TratadorMousePosicao (hv));
		}
		
		// 3x destroyers
		for (int i = 0; i < 3; i++)
		{
			Arma des = new Arma (TipoDeArma.Destroyer);
			jog.getListaArmas ().add (des);
			ArmaView dv = new ArmaView (des);
			armasViews.add (dv);
			dv.setBounds (50 + i * 25 * 2,420, 15 * 2, 15 * 2);
			c.add (dv);
			dv.setName ("dv" + (i+1));
			dv.addMouseListener (new TratadorMousePosicao (dv));
		}	
		
		mapa.setName ("mapa" + numJogador);
		mapa.addMouseListener (new TratadorMousePosicao (mapa));
		
		terminei = new JButton ("Terminei");
		terminei.setEnabled (false);
		terminei.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if (numJogador == 1)
				{
					notifyObservers ("jogador1_posicionou_armas", null);
					dispose ();
				}
				else
				{
					notifyObservers ("jogador2_posicionou_armas", null);
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
	
	public Mapa getMapa ()
	{
		return mapa;
	}
	
	public void deletaArmaView (Arma armaSelecionada)
	{
		for (ArmaView av: armasViews)
		{
			if (av.getArma () == armaSelecionada)
			{
				remove (av);
				repaint ();
			}
		}
	}
	
	public void repintaArmaView (Arma arma)
	{
		for (ArmaView av: armasViews)
			if (av.getArma () == arma)
				repaint ();
	}
	
	@Override
	public void update (String caso, Object obj)
	{
		switch (caso)
		{
			case "posicionou_todas_armas":
				terminei.setEnabled (true);
				break;
		}
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
			System.out.println ("Notificando observers!");
			ob.update (mensagem, null);
			li.remove ();
		}
	}
}