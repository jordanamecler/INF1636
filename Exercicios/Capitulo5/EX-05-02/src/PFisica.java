public class PFisica extends Contribuinte 
{ 
       protected String cpf; 
       public PFisica (String n, double r, String c) 
       {       
              nome = n;
              rendaBrt = r;
              cpf = c;
       }
       
       public double calcImposto () 
       {   
    	   if (rendaBrt <= 1400.00)
    		   return 0;
    	   else if (rendaBrt >= 1400.01 && rendaBrt <= 2100.00)
    		   return 0.1*rendaBrt - 100.00;
    	   else if (rendaBrt >= 2100.01 && rendaBrt <= 2800.00)
    		   return 0.15*rendaBrt - 270;
    	   else if (rendaBrt >= 2800.01 && rendaBrt <= 3600.00)
    		   return 0.25*rendaBrt - 500;
    	   else
    		   return 0.3*rendaBrt - 700; 
       }              
} 