package Others;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import Model.JogoDAO;

public class TratadorMenuBar implements ActionListener, ItemListener, ObservadoIF {

	private JFileChooser fc = new JFileChooser();
	private List <ObservadorIF> observers = new ArrayList <ObservadorIF> ();
	
	@Override
	public void itemStateChanged(ItemEvent e) {

		System.out.println("item state changed");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("action performed");
		JMenuItem item = (JMenuItem) e.getSource();
		
		if (item.getName() == "salvar") {
			
			int ret = fc.showSaveDialog(null);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("salvou em " + file.getName());
				
				JogoDAO dao = new JogoDAO();
				boolean salvou = dao.salvarJogo(file);
				System.out.println("salvou? " + salvou);
			}
		}
		else {
			int ret = fc.showOpenDialog(null);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				this.notifyObservers("jogo_carregado", file);
			}
		}
	}

	@Override
	public void registerObserver(ObservadorIF observer) {
		observers.add (observer);
		
	}

	@Override
	public void removeObserver(ObservadorIF observer) {
		observers.remove (observer);
	}

	@Override
	public void notifyObservers(String mensagem, Object obj) {
		ListIterator <ObservadorIF> li = observers.listIterator ();
		
		while (li.hasNext ())
		{
			ObservadorIF ob = (ObservadorIF) li.next ();
			ob.update (mensagem, obj);
			li.remove ();
		}
		
	}

}
