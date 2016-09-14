
public class Socio {
	private String nome, endereco, dtNasc;
	private int matricula;
	
	public Socio (int x)
	{
		matricula = x;
	}
	
	public String getMatric ()
	{
		String matric = Integer.toString (matricula);
		return matric.substring (0, 5) + "-" + matric.substring (5);
	}
}