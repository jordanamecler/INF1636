public class Vetor
{
	private double x;
	private double y;
	
	public Vetor ()
	{
		x = 0.0;
		y = 0.0;
	}
	
	public Vetor (double x)
	{
		this.x = x;
		y = 0.0;
	}
	
	public Vetor (double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	{
		System.out.println ("x: " + x + " y: " +  y);
	}
	
	public void soma (Vetor v)
	{
		x += v.x;
		y += v.y;
	}
	
	public void exibe ()
	{
		System.out.println (x + " , " + y);
	}
}
