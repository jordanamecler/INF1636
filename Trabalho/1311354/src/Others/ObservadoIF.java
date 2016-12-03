package Others;

public interface ObservadoIF
{
	public void registerObserver (ObservadorIF observer);
    public void removeObserver (ObservadorIF observer);
    public void notifyObservers (String mensagem, Object obj);
}	