public class Voo
{
	private Assento assentos[][];
	private int nFila;
	private int nAssentos;
	
	public Voo (int nf, int na)
	{
		nFila = nf;
		nAssentos = na;
		assentos = new Assento [nf][na];
		for (int i = 0; i < nFila; i++)
			for (int j = 0; j < nAssentos; j++)
				assentos[i][j] = new Assento (i+1, (char) (j + 65));
	}
	
	public boolean reserva (int f, char a)
	{
		int col = (int) (a - 65);
		if (assentos[f-1][col].getEstado ())
		{
			assentos[f-1][col].reserva ();
			return true;
		}
		
		return false;
	}
	
	public void imprimeMapa ()
	{
		int count = 0;
		
		System.out.print ("     ");
		for (int i = 0; i < nAssentos; i++)
			System.out.print ((char) (i + 65));
			
		System.out.println ();
		for (Assento[] lin: assentos)
		{
			count ++;
			System.out.print (String.format ("%02d", count) + " - ");
			for (Assento col: lin)
			{
				if (col.getEstado ())
					System.out.print ("L");
				else
					System.out.print ("X");
			}
			System.out.println ();
		}
	}
}
