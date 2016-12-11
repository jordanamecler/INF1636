package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.List;
import java.util.ListIterator;

import Others.ObservadorIF;

import java.io.BufferedReader;
import java.io.File;

public class JogoDAO {
	
	List <String> data = null;
	
	public boolean salvarJogo(Jogador jog1, Jogador jog2) {
		
		String infoJogo = new String();
		infoJogo += jogadorToString(jog1);
		infoJogo += jogadorToString(jog2);
		
		try {
			writeFile(infoJogo, null);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean carregarJogo() {
		
		try {
			data = readFile(null);
		}
		catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public Jogador[] getJogaresCarregados() {
		
		if (data != null) {
			Jogador jog1 = new Jogador();
			Jogador jog2 = new Jogador();
			
			ListIterator <String> li = data.listIterator ();
			
			// Info jogador 1
			jog1.setNome(li.next());
			
			int[][] mapaJog = new int[15][15];
			int[][] mapaInimigo = new int[15][15];
			
			for (int i=0; i<15; i++) {
				String linha = li.next();
				for (int j = 0; i < 15; j++) {
					mapaJog[i][j] = Character.getNumericValue(linha.charAt(j));
				}
			}
			jog1.setMeuTabuleiro(mapaJog);
			
			for (int i=0; i<15; i++) {
				String linha = li.next();
				for (int j = 0; i < 15; j++) {
					mapaInimigo[i][j] = Character.getNumericValue(linha.charAt(j));
				}
			}
			jog1.setTabuleiroInimigo(mapaInimigo);
			
			// Info jogador 2
			jog2.setNome(li.next());
			
			int[][] mapaJog2 = new int[15][15];
			int[][] mapaInimigo2 = new int[15][15];
			
			for (int i=0; i<15; i++) {
				String linha = li.next();
				for (int j = 0; i < 15; j++) {
					mapaJog2[i][j] = Character.getNumericValue(linha.charAt(j));
				}
			}
			jog2.setMeuTabuleiro(mapaJog2);
			
			for (int i=0; i<15; i++) {
				String linha = li.next();
				for (int j = 0; i < 15; j++) {
					mapaInimigo2[i][j] = Character.getNumericValue(linha.charAt(j));
				}
			}
			jog2.setTabuleiroInimigo(mapaInimigo2);
			
			return new Jogador[] {jog1, jog2};
		}
		
		return null;
		
	}
	
	private String jogadorToString(Jogador jog) {
		String jogadorInfo = String.format("%s\n", jog.getNome());
		jogadorInfo += String.format("%s\n", jog.getNumTiros());
		
		int mapa[][] = jog.getMeuTabuleiro();
		jogadorInfo += mapaToString(mapa);
		mapa = jog.getTabuleiroInimigo();
		jogadorInfo += mapaToString(mapa);
		
		return jogadorInfo; 
	}
	
	private String mapaToString(int[][] mapa) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				sb.append(mapa[i][j]);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	private void writeFile(String str, File file) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			pw.print(str);
			pw.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			throw(e);
		}
	}
	
	private List <String> readFile(File file) throws IOException {
		List <String> data = new ArrayList <String> ();
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader textReader = new BufferedReader(fr);
			
			String line;
			while ((line = textReader.readLine()) != null) {
				data.add(line); 
			}
			
			textReader.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			throw(e);
		}
		return data;
	}
}
