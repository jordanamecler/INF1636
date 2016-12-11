package View;

import javax.swing.JFrame;

import Others.ObservadorIF;

public class MenuFrame extends JFrame implements ObservadorIF
{ 
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private MenuJogo menubar;
	
	public MenuFrame (boolean salvarBool, boolean carregarBool)
	{	
		menubar = new MenuJogo ();
		menubar.habilitaSalvar (salvarBool);
		menubar.habilitaCarregar (carregarBool);
		
		this.setJMenuBar (menubar);
	}
	
	public void registraObserverMenu (ObservadorIF obs)
	{
		this.menubar.tratador.registerObserver (obs);
		System.out.println ("registrou saver");
	}

	@Override
	public void update (String caso, Object obj)
	{	
		switch (caso)
		{
		case "salvar":
			menubar.habilitaSalvar ((boolean) obj);
			break;
		case "carregar":
			menubar.habilitaCarregar ((boolean) obj);
			break;
		}
	}
}
