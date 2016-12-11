package View;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Others.TratadorMenuBar;

public class MenuJogo extends JMenuBar {

	private JMenu menu;
	private JMenuItem salvarItem;
	private JMenuItem carregarItem;
	
	public MenuJogo() {
		
		menu = new JMenu("Arquivo");
		
		TratadorMenuBar tratador = new TratadorMenuBar();
		
		salvarItem = new JMenuItem("Salvar como...");
		salvarItem.addActionListener(tratador);
		salvarItem.setName("salvar");
		menu.add(salvarItem);
		
		carregarItem = new JMenuItem("Carregar");
		carregarItem.addActionListener(tratador);
		carregarItem.setName("carregar");
		menu.add(carregarItem);
		
		this.add(menu);
	}
	
	public void habilitaSalvar(boolean value) {
		salvarItem.setEnabled(value);
	}
	
	public void habilitaCarregar(boolean value) {
		carregarItem.setEnabled(value);
	}
}
