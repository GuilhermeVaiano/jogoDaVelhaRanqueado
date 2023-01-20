package tic_tac_toe;

import java.util.Scanner;
/**
 * Classe de principal do jogo da velha, responsavel por gerar uma partida.
 * Possui como atributo uma matriz 3x3 da classe Campo.
 * @author Guilherme Vaiano
 *
 */
public class Tabuleiro {
	Campo[][] campo = new Campo[3][3];
	Boolean jogoAtivo;
	char simboloDaVez;
	char vitoria;
	Scanner scan = new Scanner(System.in);
	
	/**
	 * Construtor que inicia o jogo, atribuindo ao jogador 1 o simbolo 'X' para jogar
	 */
	public Tabuleiro() {
		this.jogoAtivo = true;
		this.vitoria = ' ';
		this.simboloDaVez = 'X';
		iniciarJogo(campo);
	}
	
	/**
	 * Metodo responsavel por criar um jogo do tipo casual. Continua sendo executada ate algum dos jogadores vencer
	 * ou o tabuleiro do jogo ser totalmente preenchido sem vencedores. 
	 * @exception Caso o jogador tente inserir um valor de linha ou coluna incorreto, sera emitido um aviso ao jogador
	 * e a rodada atual sera reiniciada para o jogador inserir um valor valido.
	 */
	public void jogoCasual() {
		int contador = 0;
		while(jogoAtivo) {
			desenhaJogo(campo);
			vitoria = verificaVitoria(campo);
			if(vitoria != ' ') {
				System.out.printf("\n%s venceu!\n", this.vitoria);
				break;
			}
			try {
				if(validaJogada(campo, jogar(scan,simboloDaVez), simboloDaVez)) {
					simboloDaVez = (simboloDaVez == 'X'? 'O' : 'X'); // Troca o simbolo para o proximo jogador realizar sua acao
				}
				
			} catch(Exception e) {
				System.out.printf("Erro durante o progresso da jogada.");
			}
			contador++;
			if(contador == 9) { // 9 é o limite de rodadas que um jogo da velha pode ter (preenchendo totalmente a matriz 3x3.
				System.out.printf("Empate: Todos os campos foram preenchidos.\n");
				break;
			}
		}
		System.out.printf("Fim do jogo.\n");
	}

	
	/**
	 * Metodo responsavel por executar o jogo ranqueado. Ao final, retorna o simbolo do vencedor ou 'E', caso de empate.
	 * @return
	 */
	public char jogoRanqueado() {
		int contador = 0;
		while(jogoAtivo) {
			desenhaJogo(campo);
			vitoria = verificaVitoria(campo);
			if(vitoria != ' ') {
					break;
			}

			try {
				if(validaJogada(campo, jogar(scan,simboloDaVez), simboloDaVez)) {
					simboloDaVez = (simboloDaVez == 'X'? 'O' : 'X'); // Troca o simbolo para o proximo jogador realizar sua acao
				}
				
			} catch(Exception e) {
				System.out.printf("Erro durante o progresso da jogada.");
			}
			contador++;
			if(contador == 9) {
				return 'E';
			}
		}
		return this.vitoria;
	}
	
	/**
	 * Exibe na tela a matriz 3x3, ou seja, o tabuleiro do jogo da velha.
	 * @param campo Recebe como parametro o tabuleiro do jogo.
	 */
	public static void desenhaJogo(Campo[][] campo) {
		limparTela();
		System.out.println("    0   1    2");
		System.out.printf("0   %c | %c | %c  %n", campo[0][0].getSimbolo(),
				                                  campo[0][1].getSimbolo(),
				                                  campo[0][2].getSimbolo());
		System.out.println("   ------------ ");
		System.out.printf("1   %c | %c | %c  %n", campo[1][0].getSimbolo(),
                                                  campo[1][1].getSimbolo(),
                                                  campo[1][2].getSimbolo());
		System.out.println("   ------------ ");
		System.out.printf("2   %c | %c | %c  %n", campo[2][0].getSimbolo(),
                								  campo[2][1].getSimbolo(),
                								  campo[2][2].getSimbolo());
	}
	
	/**
	 * Realiza a limpeza da tela para melhorar a visualizacao do usuario.
	 */
	public static void limparTela() {
		for(int cont = 0; cont<200; cont++) {
			System.out.println("");
		}
	}
	
	/**
	 * Solicita para o jogador da rodada atual o numero da linha e da coluna que ele deseja jogar (0 <= x <= 2)
	 * @param scan Recebe o objeto Scanner criado na Main
	 * @param jogada Recebe o simbolo da rodada atual (X ou O)
	 * @return Retorna as coordenadas da coluna e linha jogadas.
	 */
	public static int[] jogar(Scanner scan, char jogada) {
		int p[] = new int[2];
		System.out.printf("%s %c%n","É a vez de: ",jogada);
		System.out.print("Qual linha deseja jogar?: ");
		p[0] = scan.nextInt();
		System.out.print("Qual coluna deseja jogar?: ");
		p[1] = scan.nextInt();
		return p;
	}
	
	/**
	 * Verifica se o valor da linha e da coluna escolhida pelo jogador sera uma jogada valida
	 * @param campo Tabuleiro do jogo (matriz 3x3)
	 * @param p Vetor com as coordenadas da linha e coluna digitadas pelo jogador
	 * @param simboloDaVez Recebe o simbolo do jogador atual (X ou O)
	 * @return true se a jogador for valida e false se a jogada nao for aceita (por conta do campo ja estar preenchido)
	 */
	public static Boolean validaJogada(Campo[][] campo, int p[], char simboloDaVez) {
		if(campo[p[0]][p[1]].getSimbolo() == ' ') {
			campo[p[0]][p[1]].setSimbolo(simboloDaVez);
			return true;
		} else {
			System.out.println("O campo já está preenchido! Por favor, informe um campo vazio.");
			try {
				Thread.sleep(2000); // "Pausa" a execucao por 2 segundos para o jogador visualizar o erro de sua jogada.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * Funcao responsavel por criar as referencias dos campos do tabuleiro do jogo, gerando uma matriz 3x3 de campos vazios.
	 * @param campo
	 */
	public static void iniciarJogo(Campo[][] campo) {
		for(int linha=0; linha<3; linha++)
			for (int coluna=0; coluna<3;coluna++)
				campo[linha][coluna] = new Campo();
	}

	/**
	 * Metodo que verifica se o jogador da rodada anterior completou a condicao de vitoria
	 * @param campo Recebe o tabuleiro com os campos preenchidos de acordo com a rodada atual
	 * @return Retorna o simbolo ganhador da partida.
	 */
	public char verificaVitoria(Campo[][] campo) {
		char vencedor = (simboloDaVez == 'X' ? 'O' : 'X');
		if(verificaLinhas(campo)) return vencedor;
		if(verificaColunas(campo)) return vencedor;
		if(verificaDiagonais(campo)) return vencedor;
		return ' ';
	}
	
	/**
	 * Verifica se uma das tres linhas do tabuleiro esta completa e com simbolos iguais diferentes de ' '.
	 * @param campo Tabuleiro preenchido de acordo com a rodada atual.
	 * @return true se o jogador que acabou de jogar completou a condicao de vitoria e false caso nao
	 */
	private static Boolean verificaLinhas(Campo[][] campo) {
		for(int i=0; i<3; i++) {
			char val0 = campo[i][0].getSimbolo();
			char val1 = campo[i][1].getSimbolo();
			char val2 = campo[i][2].getSimbolo();
			if (val0 != ' ') {
				if((val0 == val1) && (val0 == val2) && (val1 == val2)) {
					return true;	
				}
			}
		}
		return false;
	}
	/**
	 * Verifica se uma das tres colunas do tabuleiro esta completa e com simbolos iguais diferentes de ' '.
	 * @param campo Tabuleiro preenchido de acordo com a rodada atual.
	 * @return true se o jogador que acabou de jogar completou a condicao de vitoria e false caso nao
	 */
	private static Boolean verificaColunas(Campo[][] campo) {
		for(int i=0; i<3; i++) {
			char val0 = campo[0][i].getSimbolo();
			char val1 = campo[1][i].getSimbolo();
			char val2 = campo[2][i].getSimbolo();
			if (val0 != ' ') {
				if((val0 == val1) && (val0 == val2) && (val1 == val2)) {
					return true;	
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Verifica se uma das duas diagonais do tabuleiro esta completa e com simbolos iguais diferentes de ' '.
	 * @param campo Tabuleiro preenchido de acordo com a rodada atual.
	 * @return true se o jogador que acabou de jogar completou a condicao de vitoria e false caso nao
	 */
	private static Boolean verificaDiagonais(Campo[][] campo) {
		if(campo[1][1].getSimbolo() != ' ') {
			if ((campo[1][1].getSimbolo() == campo[0][0].getSimbolo())) {
				if(campo[0][0].getSimbolo() == campo[2][2].getSimbolo()) {
					return true;
				}
			}
			
			if(campo[1][1].getSimbolo() == campo[0][2].getSimbolo()) {
				if(campo[0][2].getSimbolo() == campo[2][0].getSimbolo()) {
					return true;
				}
			}
		}
		return false;
	}
		
}
		    
	

