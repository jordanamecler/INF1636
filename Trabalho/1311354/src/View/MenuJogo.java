package View;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Others.TratadorMenuBar;

public class MenuJogo extends JMenuBar
{
	private static final long serialVersionUID = 7526472295622776147L;  // unique id
	private JMenu menu;
	private JMenuItem salvarItem;
	private JMenuItem carregarItem;
	protected TratadorMenuBar tratador;
	
	public MenuJogo ()
	{
		
		menu = new JMenu ("Arquivo");
		
		tratador = new TratadorMenuBar ();
		
		salvarItem = new JMenuItem ("Salvar como...");
		salvarItem.addActionListener (tratador);
		salvarItem.setName ("salvar");
		menu.add (salvarItem);
		
		carregarItem = new JMenuItem ("Carregar");
		carregarItem.addActionListener (tratador);
		carregarItem.setName ("carregar");
		menu.add (carregarItem);
		
		this.add (menu);
	}
	
	public void habilitaSalvar (boolean value)
	{
		salvarItem.setEnabled (value);
	}
	
	public void habilitaCarregar (boolean value)
	{
		carregarItem.setEnabled (value);
	}
}