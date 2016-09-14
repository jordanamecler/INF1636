public class Matematica
{
	public static double pi (int n)
	{
		double result = 1.0, div = 3;
		
		for (int i = 0; i < n - 1; i++)
		{
			if (i % 2 == 0)
				result -= 1/div;
			else
				result += 1/div;
			
			div += 2;
		}

		return result * 4;
	}
}