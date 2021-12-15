package br.com.windows.cm;

import br.com.windows.cm.modelo.Tabuleiro;
import br.com.windows.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		
		new TabuleiroConsole(tabuleiro);
		
		
		
	}

}
