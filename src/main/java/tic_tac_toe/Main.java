package tic_tac_toe;

import java.util.Scanner;

import repositorio.RepositorioJogador;

public class Main {
	
	public static void main(String[] args) {
		Tabuleiro jogo = new Tabuleiro();
		RepositorioJogador rj = new RepositorioJogador();
		Scanner read = new Scanner(System.in);
		System.out.println("Bem-vindo ao Jogo da Velha!");
		System.out.println("Como deseja jogar?\n (1) Jogo Casual\n (2) Jogo Ranqueado\n (3) Consultar Ranque");
		int modoJogo = read.nextInt();
		
		while(modoJogo < 1 || modoJogo >2) {
			if (modoJogo == 3) {
				rj.mostrarRanque();
				System.out.println("Como deseja jogar?\n (1) Jogo Casual\n (2) Jogo Ranqueado\n");
			} else {
			System.out.println("Entrada invalida! Digite um numero valido");
			}
			modoJogo = read.nextInt();
		}
		
		switch(modoJogo) {
		case 1:
			jogo.jogoCasual();
			break;
			
		case 2:
			read.nextLine();
			Jogador j1 = null;
			Jogador j2 = null;
			for (int i = 1; i<3; i++) {
				System.out.printf("JOGADOR %d:", i);
				System.out.printf("O jogador %d jogará com %s\n", i, (i == 1? "X" : "O"));
				System.out.println("Ja possui jogador cadastrado? (Y/N)");
				String possuiCadastro = read.nextLine();
				while (!(possuiCadastro.equalsIgnoreCase("Y") || possuiCadastro.equalsIgnoreCase("N"))) {
					System.out.printf("Por favor, digite uma entrada valida (Y/N):");
					possuiCadastro = read.nextLine();
				}
			
				if(possuiCadastro.equalsIgnoreCase("Y")) {
					System.out.printf("JOGADOR %d:", i);
					if (i== 1) {
						j1 = login(read);
						System.out.println("Jogador 1 logado com sucesso!");
						j1.dadosJogador();
					} else {
						j2 = login(read);
						System.out.println("Jogador 2 logado com sucesso!");
						j2.dadosJogador();
					}
				
				} else {
					System.out.printf("Digite o nome do jogador %d:", i);
					if (i == 1) {
						String nj1 = read.nextLine();
						j1 = rj.criarJogador(nj1);
					} else {
						String nj2 = read.nextLine();
						j2 = rj.criarJogador(nj2);
						
					}
				}
			}
			char vitoria = jogo.jogoRanqueado();
			if (vitoria == 'X') {
				j1.addVitoria();
				j2.addDerrota();
				System.out.printf("\n%s venceu!\n", j1.getNome());
			} else if (vitoria == 'O') {
				j1.addDerrota();
				j2.addVitoria();
				System.out.printf("\n%s venceu!\n", j2.getNome());
			} else {
				System.out.printf("Empate: Todos os campos foram preenchidos.\n");
				System.out.printf("Fim do jogo.\n");
				System.exit(0);
			}
			j1.calculaWinRate();
			j2.calculaWinRate();
			rj.atualizar(j1);
			rj.atualizar(j2);
			System.out.println("Dados de ambos os jogadores atualizados na classificacao");
			System.out.printf("Fim do jogo.\n");
				}

	}


	private static Jogador login(Scanner read) {
		RepositorioJogador rj = new RepositorioJogador();
		System.out.printf("Digite o nome do jogador:");
		String j = read.nextLine();
		Jogador jogador = rj.iniciarJogador(j);
		return jogador;
		
	}
	

}