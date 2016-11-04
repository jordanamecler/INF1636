import javax.swing.*;

public class PosicionarFrame extends JFrame
{
	public final int LARG_DEFAULT = 900;
	public final int ALT_DEFAULT = 600;
	
	public PosicionarFrame ()
	{
		setLayout (null);
		setTitle ("Batalha Naval");
		
		System.out.println(InformacoesGlobais.getInformacoesGlobais().getJogador(1));
		
		setSize (LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
	}
}