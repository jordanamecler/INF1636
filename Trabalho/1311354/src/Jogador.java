public class Jogador
{
	private String nome;
	private int[][] meuTabuleiro = new int[15][15];
	private int[][] tabuleiroInimigo = new int[15][15];

	public String getNome ()
	{
		return nome;
	}

	public void setNome (String nome)
	{
		this.nome = nome;
	}

	public int[][] getTabuleiroInimigo ()
	{
		return tabuleiroInimigo;
	}

	public int[][] getMeuTabuleiro ()
	{
		return meuTabuleiro;
	}
	
	public void marcarMeuTabuleiro (int x, int y)
	{
		this.meuTabuleiro[x][y] = 1;
	}
	
	public void marcarTabuleiroInimigo (int x, int y)
	{
		this.tabuleiroInimigo[x][y] = 1;
	}
}