public class EX0501 
{ 
	public static void main (String[] args) 
    {       
		Contribuinte []lst;
        lst = Contribuinte.listaContr ();
        System.out.printf ("NOME                       IMPOSTO\n"); 
        System.out.printf ("====================       =======\n\n"); 
           
        for (Contribuinte contribuinte: lst)
            System.out.printf ("%-20s     %9.2f\n", contribuinte.nome, contribuinte.calcImposto());  
    }
}