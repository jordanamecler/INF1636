import javax.swing.*;
import java.awt.*;

public class PosicionarFrame extends JFrame
{
	private final int LARG_DEFAULT = 900;
	private final int ALT_DEFAULT = 600;
	
	public PosicionarFrame ()
	{
		Toolkit tk = Toolkit.getDefaultToolkit ();
		Dimension screenSize = tk.getScreenSize ();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_DEFAULT/2;
		int y = sa/2 - ALT_DEFAULT/2;
		
		setBounds (x, y, LARG_DEFAULT, ALT_DEFAULT);
		
		setSize (LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		
		getContentPane ().add (new Panel ());
		
		System.out.println(InformacoesGlobais.getInformacoesGlobais().getJogador(1));
		
	}
}