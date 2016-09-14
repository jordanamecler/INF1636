public class Voo
{
	private Assento assentos[][];
	private int nFila;
	private int nAssentos;
	
	public Voo (int nf, int na)
	{
		char as;
		nFila = nf;
		nAssentos = na;
		assentos = new Assento [nf][na];
	}
	
	public boolean reserva (int f, char a)
	{
		if (assentos[f][f].getEstado ())
		{
			assentos[f][f].reserva ();
			return true;
		}
		
		return false;
	}
	
	public void imprimeMapa ()
	{
		
	}
}
