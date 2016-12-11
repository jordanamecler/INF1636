package Others;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

public class TratadorMenuBar implements ActionListener, ItemListener {

	private JFileChooser fc = new JFileChooser();
	
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
				// notifica jogoDAO;
				System.out.println("salvou em " + file.getName());
			}
		}
		else {
			int ret = fc.showOpenDialog(null);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// notifica jogoDAO;
				System.out.println("carregou " + file.getName());
			}
		}
	}

}
