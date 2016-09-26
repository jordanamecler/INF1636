public class PJuridica extends Contribuinte 
{ 
       protected String cnpj; 
       
       public PJuridica (String n, double r, String c) 
       {       
    	   nome = n;
    	   rendaBrt = r;
    	   cnpj = c;
       }      
       
       public double calcImposto () 
       {       
    	   return 0.1*rendaBrt;
       }       
} 