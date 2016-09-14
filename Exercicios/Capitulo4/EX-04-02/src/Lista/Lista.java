package Lista;

public class Lista
{
	private int tam = 0;
	private No ini 	= null;
	private No fin 	= null;
	private No corr = null;

	public boolean vazio ()
	{
		return tam == 0 ? true : false;
	}
	
	public boolean insIni (Object x)
	{
		No aux 	 = ini;
		No newNo = new No (x, aux);
		ini = newNo;
		
		if (tam == 0)
			fin = ini;
		
		tam++;
		return true;
	}
	
	public boolean insFin (Object x)
	{
		if (tam == 0)
			return insIni (x);
		
		No ult = new No (x, null);
		fin.setProx (ult);
		fin = ult;
		tam++;
		return true;
	}
	
	public Object retIni ()
	{
		if (tam == 0)
			return null;
		
		Object elem = ini.getElem ();
		ini = ini.getProx ();
		tam--;
		return elem;
	}
	
	public Object retFin ()
	{
		if (tam == 0)
			return null;		
		
		while (corr.getProx () != null)
			corr = corr.getProx ();
		
		Object elem = fin.getElem ();
		fin = corr;
		tam--;
		return elem;
	}
	
	public void posIni ()
	{
		corr = ini;
	}
	
	public Object prox ()
	{
		if (corr == null)
			return null;
		
		Object lastCorr = corr.getElem ();
		corr = corr.getProx ();
		return lastCorr;
	} 
}