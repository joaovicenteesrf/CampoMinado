package br.com.windows.cm.modelo;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.windows.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	@Test
	void vizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoDistancia1Cima() {
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoDistancia1Abaixo() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoDistancia2() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void naoVizinhoDistancia2() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasVezes() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			assertFalse(campo.abrir());			
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeReiniciar() {
		Campo vizinho = new Campo(2,2);
		vizinho.minar();
		vizinho.alternarMarcacao();
		campo.abrir();
		campo.reiniciar();
		vizinho.reiniciar();
		
		assertFalse(campo.isAberto() && campo.isMarcado());
		assertFalse(vizinho.isMinado());
		
	}
	
	@Test
	void testeToString() {
		Campo vizinho = new Campo(3,4);
		Campo vizinho2 = new Campo(2,2);
		vizinho.minar();
		campo.abrir();
		vizinho.alternarMarcacao();
		campo.toString();
		vizinho.toString();
		assertTrue(vizinho.toString().contains("x"));
		vizinho.alternarMarcacao();
		vizinho.toString();
		assertTrue(campo.toString().contains(" "));
		assertTrue(vizinho.toString().contains("?"));
		assertTrue(vizinho2.toString().contains("?"));
	}
	
	@Test
	void testeObjetivoAlcancado() {
		Campo vizinho = new Campo(2,2);
		Campo vizinhoFechado = new Campo(3,4);
		vizinho.minar();
		campo.abrir();
		vizinho.alternarMarcacao();
		campo.objetivoAlcancado();
		vizinho.objetivoAlcancado();
		assertTrue(campo.objetivoAlcancado());
		assertTrue(vizinho.objetivoAlcancado());
		assertFalse(vizinhoFechado.objetivoAlcancado());
		
	}
	
	@Test
	void testeIniciarTabuleiro() {
		Tabuleiro teste = new Tabuleiro(4, 3, 2);
		boolean resultado = teste.getColunas() == 3 && teste.getLinhas() == 4 && teste.getMinas() == 2;
		assertTrue(resultado);
	}
	
	
	@Test
	void abrirNoTabuleiro() {
		Tabuleiro teste = new Tabuleiro(3,3,3);
		teste.abrir(1, 1);
		teste.abrir(8, 8);
		teste.alterarMarcacao(0, 0);
	}
	
	@Test
	void reiniciarTabuleiro() {
		Tabuleiro teste = new Tabuleiro(4,5,3);
		teste.abrir(1, 1);
		teste.reiniciar();
		Campo campo = new Campo(1,1);
		assertFalse(campo.isAberto());	
	}
	
//fim do algoritmo	
}
