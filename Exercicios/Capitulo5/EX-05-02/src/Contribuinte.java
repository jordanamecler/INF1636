import Lista.*;

public abstract class Contribuinte 
{ 
       protected String nome; 
       protected double rendaBrt; 
       public static Lista listaContr () 
       {
    	   Lista lista = new Lista ();      
           lista.insIni (new PFisica ("Joao Santos", 3000.00, "11111"));       
           lista.insIni (new PJuridica ("Lojas AA", 150000.00, "10055"));       
           lista.insIni (new PFisica ("Maria Soares", 5000.00, "22222"));       
           lista.insIni (new PJuridica ("Supermercados B", 2000000.00, "10066"));       
           lista.insIni (new PJuridica ("Posto XX", 500000.00, "10077"));       
           lista.insIni (new PFisica ("Carla Maia", 1500.00, "33333"));       
           return lista;       
       }       
       public String getNome () 
       {       
              return nome;       
       }       
       abstract public double calcImposto (); 
} 