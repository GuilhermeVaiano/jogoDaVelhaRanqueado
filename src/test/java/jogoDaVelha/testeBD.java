package jogoDaVelha;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import repositorio.RepositorioJogador;
import tic_tac_toe.Jogador;

public class testeBD {

	public static void main(String[] args) {
		/*
		 * Jogador player = new Jogador("Jorge"); VERIFICA SE UM JOGADOR COM O NOME JA
		 * EXISTE em.getTransaction().begin(); try { em.persist(player);
		 * 
		 * }catch (javax.persistence.PersistenceException e ) {
		 * System.out.println("O usuario ja esta cadastrado!"); }
		 * em.getTransaction().commit(); em.close(); emf.close();
		 */

		// Jogador j = em.find(Jogador.class, 1); System.out.println(j.getNome());

		/*
		 * ESTRUTURA PARA CONSTRUIR UMA QUERY String jpql =
		 * "select c from Jogador c order by nome ASC "; int id = 1;
		 * 
		 * TypedQuery<Jogador> typedQuery = em .createQuery(jpql, Jogador.class);
		 * //.setParameter("id",id); List<Jogador> listaJogador =
		 * typedQuery.getResultList();
		 * 
		 * for(Jogador jogador : listaJogador) { System.out.println(jogador.getNome());
		 * }
		 */

		/*
		 * METODO OK Jogador j = new Jogador(); j.calculaWinRate();
		 */
		/*
		 * // METODO OK RepositorioJogador rj = new RepositorioJogador();
		 * //rj.mostrarRanque(); rj.iniciarJogador("Jorge");
		 */
		
		RepositorioJogador rj = new RepositorioJogador();
		String nome = "Jorge";
		rj.mostrarRanque();
		/*
		 * List<Jogador> js = rj.listarDados();
		 * 
		 * for (Jogador jogador : js) {
		 * System.out.printf("| %-16s | %-8d | %-8d | %-7s |\n", jogador.getNome(),
		 * jogador.getQtdVitorias(), jogador.getQtdDerrotas(),
		 * jogador.getTaxaVitoria()); }
		 */
		
	}

}
