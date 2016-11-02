import java.awt.*;

public class Grid extends Canvas
{
	private int numRows;
	private int numColumns;
	private int width;
	private int height;
	
	public Grid (int width, int height, int rows, int columns)
	{
		setSize (width = width, height = height);
		numRows = 15;
		numColumns = 15;
	}
	
	public void paint (Graphics g)
	{
		width = getSize ().width;
		height = getSize ().height;
		
		int heightOfRow = height / numRows;
		int k;
		for (k = 0; k < numRows; k++)
		{
			g.drawLine (0, k * heightOfRow, width, k * heightOfRow);
		}
		
		int widthOfRow = width / numColumns;
		for (k = 0; k < numColumns; k++)
		{
			g.drawLine (k * widthOfRow, 0, k * widthOfRow, height);
		}
	}
}