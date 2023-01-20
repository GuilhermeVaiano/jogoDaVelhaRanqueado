package repositorio;

import tic_tac_toe.Jogador;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 * Classe responsavel por realizar a persistencia de dados entre os objetos Jogador e o banco de dados.
 * @author Windows
 *
 */
public class RepositorioJogador {
	private EntityManagerFactory emf;
	private EntityManager em;

	/**
	 * Construtor da classe
	 */
	public RepositorioJogador() {
		emf = Persistence.createEntityManagerFactory("jogadores");
		em = emf.createEntityManager();
	}

//-------------------------------CRUD JOGADOR---------------------------------------------------
	// Verifica se ja existe um jogador com o nome informado e cria o objeto no BD
	public Jogador criarJogador(String nome) {
		while (existsJogador(nome)) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Usuario já existe! Por favor, digite outro nome");
			nome = scan.nextLine();
		}
		Jogador j = new Jogador(nome);
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		return j;
	}

	// Atualiza o objeto no Banco de Dados
	public void atualizar(Jogador j) {
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
	}

	// Exclui o cadastro do jogador. 
	// Obs.: Criado apenas p/ demonstraçao. Método não utilizado.
	private void remover(Jogador j) {
		em.getTransaction().begin();
		em.remove(j);
		em.getTransaction().commit();
	}
	
//----------------------------METODOS PARA DADOS CADASTRADOS----------------------------
	// Cria uma lista contendo todos os jogadores cadastrados.
	@SuppressWarnings("unchecked")
	public List<Jogador> listarDados() {
		em.getTransaction().begin();
		Query query = em.createQuery("select j from Jogador j order by j.qtdVitorias DESC");
		List<Jogador> listaJogador = query.getResultList();
		em.getTransaction().commit();
		return listaJogador;
	}

	// Mostra o ranque dos jogadores cadastrados
	public void mostrarRanque() {
		List<Jogador> players = listarDados();
		System.out.println(" --------------------------------------------------");
		System.out.println("|               Ranque de jogadores                |");
		System.out.println("|--------------------------------------------------|");
		System.out.println("|       NOME       | VITORIAS | DERROTAS | WINRATE |");
		for (Jogador jogador : players) {
			System.out.printf("| %-16s | %-8d | %-8d | %-7s |\n", jogador.getNome(), jogador.getQtdVitorias(),
																jogador.getQtdDerrotas(), jogador.getTaxaVitoria());
		}
		System.out.println("|--------------------------------------------------|");
	}

	// Recupera os dados de um objeto no banco de dados
	public Jogador iniciarJogador(String nome) {
		Jogador j = null;
		em.getTransaction().begin();
		try {
			String jpql = "select j from Jogador j where nome = :nome";
			TypedQuery<Jogador> tQuery = em.createQuery(jpql, Jogador.class)
							               .setParameter("nome", nome);
			j = tQuery.getSingleResult();
			em.getTransaction().commit();
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Nao existe nenhum jogador cadastrado "
							   + "com esse nome.\nCriando um novo cadastro com o nome fornecido...");
			em.getTransaction().commit();
			j = criarJogador(nome);
		}
		return j;
	}
	
	// Verifica se existe um jogador cadastrado no banco de dados com o nome informado.
	public boolean existsJogador(String nome) {
		em.getTransaction().begin();
		String jpql = "select count(1) from Jogador where nome = :nome";
		Query query = em.createQuery(jpql).setParameter("nome",nome);
		Long resultado = (Long)query.getSingleResult();
		System.out.println(resultado);
		em.getTransaction().commit();
		if(resultado == 0) return false;
		else return true;
	}

}
