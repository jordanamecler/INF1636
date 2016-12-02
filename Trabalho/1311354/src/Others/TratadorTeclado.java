package Others;

import Model.InformacoesGlobais;
import Model.Jogador;
import View.PosicionarNavios;
import Model.Arma;
import Model.EstadoPosicionamento;

import java.awt.event.*;

public class TratadorTeclado implements KeyListener
{

    public void keyReleased (KeyEvent e)
    {
    	int keyCode = e.getKeyCode ();
    	
    	if (keyCode == 27)
    	{
    		// Tecla ESC
    		Jogador jogCorrente = InformacoesGlobais.getInformacoesGlobais ().getJogadorCorrente ();
    		Arma arma = jogCorrente.getArmaEmEstado (EstadoPosicionamento.Girando);
    		if (arma != null)
    		{
    			arma.setEstadoPosicionamento (EstadoPosicionamento.Posicionada);
    			PosicionarNavios frame = (PosicionarNavios) e.getSource ();
				frame.deletaArmaView (arma);
				jogCorrente.atualizaTabuleiro ();
				frame.getMapa ().marcaMapa (jogCorrente.getMeuTabuleiro ());
				jogCorrente.verificaArmasUsadas ();
    		}
    	}
    }
    
	@Override
	public void keyTyped (KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void keyPressed (KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
}