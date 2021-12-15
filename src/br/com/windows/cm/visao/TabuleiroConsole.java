package br.com.windows.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.windows.cm.excecao.ExplosaoException;
import br.com.windows.cm.excecao.SairException;
import br.com.windows.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				
				System.out.println("Deseja jogar novamente? (S/n) ");
				String resposta = entrada.nextLine();
				
				if (resposta.equalsIgnoreCase("n")) {
					continuar = false;					
				} else {
					tabuleiro.reiniciar();
				}
			}
			
		} catch (SairException e) {
			System.out.println("Fim de jogo!");
		} finally {
			entrada.close();
		}
		
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x,y): ");
			
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
			
				digitado = capturarValorDigitado("1 - Abrir ou 2- (Des)Marcar: ");
			
				if(digitado.equals("1")) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if (digitado.equals("2")) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
				
				
			}
			
			
			System.out.println("Você ganhou!!");
			System.out.println(tabuleiro);
		} catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!!");
		}
		
	}

	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if (digitado.equalsIgnoreCase("sair")) {
			throw new SairException();
		}
		return digitado;
	}

	
// Fim do algoritmo
}
