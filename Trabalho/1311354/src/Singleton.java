public class Singleton
{
	private static Singleton uniqueInstance = new Singleton ();
	public String jogador1;
	public String jogador2;
	
	private Singleton ()
	{
	}

	public static Singleton getInstance () {
		return uniqueInstance;
	}
}