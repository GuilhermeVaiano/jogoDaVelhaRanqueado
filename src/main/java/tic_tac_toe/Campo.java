package tic_tac_toe;

/**
 * Classe responsavel por gerar os campos do tabuleiro
 * @author Guilherme Vaiano
 *
 */
public class Campo {
	private char simbolo;
	
	/**
	 * Construtor da classe
	 */
	public Campo() {
		this.simbolo = ' ';
	}


	public char getSimbolo() {
		return simbolo;
	}

	/**
	 * Metodo responsavel por armazenar um valor do jogo (X ou O) e de verificar se o campo esta preenchido ou nao.
	 * @param simbolo Recebe o simbolo do jogador atual (X ou O)
	 */
	public void setSimbolo(char simbolo) {
		if (this.simbolo == ' ') {
			this.simbolo = simbolo;
		}else {
			System.out.println("O campo já está preenchido!");
		}
	}
	
	/**
	 * Metodo responsavel por "limpar" o campo, ou seja, deixa-lo sem simbolos.
	 */
	public void limpaCampo() {
		this.simbolo = ' ';
	}
}