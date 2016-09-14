public class EX0401
{
	public static void main (String[] args)
	{
		Vetor vector 	= new Vetor (3.35464, 4.68242);
		Vetor newVector = vector.clone ();
		
		System.out.println (newVector.toString ());
	}
}