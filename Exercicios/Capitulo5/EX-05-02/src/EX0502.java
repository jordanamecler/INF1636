import Lista.*;

public class EX0502
{
	public static void main(String[] args)
	{
		Lista lista = Contribuinte.listaContr ();
		
		lista.posIni ();
		Contribuinte cont = (Contribuinte) lista.prox ();
		System.out.printf ("NOME                       IMPOSTO\n"); 
        System.out.printf ("====================       =======\n\n");
		while (cont != null)
		{
			System.out.printf ("%-20s     %9.2f\n", cont.nome, cont.calcImposto ());
			cont = (Contribuinte) lista.prox ();
		}
	}
}