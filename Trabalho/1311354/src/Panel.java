import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel
{
	public static final int TXT_X = 300;
	public static final int TXT_Y = 300;
	
	public Panel ()
	{
		setBounds (500, 100, TXT_X, TXT_Y);
		setBackground (Color.WHITE);
	}
}