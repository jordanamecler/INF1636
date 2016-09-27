public class Assento
{
	private int fila;
	private char assento;
	private boolean livre = true;
	
	public Assento (int f, char a)
	{
		fila = f;
		assento = a;
	}
	
	public boolean getEstado ()
	{
		return livre;
	}
	
	public void reserva ()
	{
		livre = false;
	}
}
