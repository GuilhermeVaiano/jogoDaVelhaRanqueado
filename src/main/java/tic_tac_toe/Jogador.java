package tic_tac_toe;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe jogador, feita de acordo com a JPA para realizar a persistencia de dados com o banco de dados
 * Classe responsavel por armazenar as estatisticas do jogador cadastrado.
 * @author Windows
 *
 */
@Entity
@Table (name = "jogador")
public class Jogador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; // Atributo no qual o valor eh gerado automaticamente pelo SQL
	@Column private String nome;
	@Column private Integer qtdVitorias;
	@Column private Integer qtdDerrotas;
	@Column private String taxaVitoria;
	
//-------------------CONSTRUTORES------------------------------------
	
	public Jogador() {
		
	}
	
	/**
	 * Construtor responsavel por gerar as estatisticas do jogador novo
	 * @param nome Nome do jogador
	 */
	public Jogador(String nome) {
		this.setNome(nome);
		this.id = null;
		this.qtdVitorias = 0;
		this.qtdDerrotas = 0;
		this.taxaVitoria = "--";
	}

//-------------------GETTERS E SETTERS------------------------------
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtdVitorias() {
		return qtdVitorias;
	}

	public void addVitoria() {
		this.qtdVitorias++;
	}

	public Integer getQtdDerrotas() {
		return qtdDerrotas;
	}

	public void addDerrota() {
		this.qtdDerrotas++;
	}
	
	public String getTaxaVitoria() {
		return this.taxaVitoria;
	}
	
	
	
//-----------------------METODOS--------------------------------
	
	/**
	 * Metodo que calcula a taxa de vitoria do jogador (qtd de vitoras/ (qtd de vitorias + qtd de derrotas) e atualiza-la nas estatisticas.
	 */
	public void calculaWinRate() {
		DecimalFormat df = new DecimalFormat("##,##0.00%");
		df.setRoundingMode(RoundingMode.DOWN);
		int v = this.qtdVitorias;
		int d = this.qtdDerrotas;
		double rating = (double)v / (v+d);
		this.taxaVitoria = df.format(rating);
		
	}
	

	
//-----------------------FUNCOES--------------------------------
	
	/**
	 * Exibe a quantidade de vitórias, derrotas e a taxa de vitória do jogador.
	 */
	public void dadosJogador() {
		System.out.printf("Jogador: %-5s V: %-2d D: %-2d WinRate: %s\n", this.getNome(),
																		 this.getQtdVitorias(),
																		 this.getQtdDerrotas(),
																		 this.getTaxaVitoria());
	}
	
}