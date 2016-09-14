public class Vetor
{	
	private double x, y;

	public Vetor (double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	private Vetor ()
	{
	}
	
	public Vetor clone ()
	{
		Vetor newVector = new Vetor ();
		
		newVector.x = x;
		newVector.y = y;
		
		return newVector;
	}
	
	public String toString ()
	{ 
		return String.format ("( %.2f , %.2f )", x, y);
	}
}
