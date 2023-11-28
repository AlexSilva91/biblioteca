package model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import connectionFactory.ConnectionFactory;
import model.entities.Livros;

public class LivroService {
	private final EntityManager em = ConnectionFactory.getConnection();

	public Livros saveLivro(Livros livro) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(livro);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.getMessage();
		}
		return livro;
	}

	public Livros findById(long id) {
		return this.em.find(Livros.class, id);
	}

	// SELECT u FROM Usuario as u where u.endereco.rua =: road
	@SuppressWarnings("unchecked")
	public List<Livros> listAllFindByTitle(String title) {
		String jpql = "SELECT l FROM Livros as l where l.titulo =: title";
		Query query = em.createQuery(jpql, Livros.class).setParameter("title", title);
		List<Livros> listAllFindByTitle = query.getResultList();
		return listAllFindByTitle;
	}

	@SuppressWarnings("unchecked")
	public List<Livros> listAllFindByAuthor(String author) {
		String jpql = "SELECT l FROM Livros as l where l.autor =: author";
		Query query = em.createQuery(jpql, Livros.class).setParameter("author", author);
		List<Livros> listAllFindByAuthor = query.getResultList();
		return listAllFindByAuthor;
	}

	@SuppressWarnings("unchecked")
	public List<Livros> listAllFindByYear(long year) {
		String jpql = "SELECT l FROM Livros as l where l.ano =: year";
		Query query = em.createQuery(jpql, Livros.class).setParameter("year", year);
		List<Livros> listAllFindByYear = query.getResultList();
		return listAllFindByYear;
	}

	public List<Livros> listAll() {
		String jpql = "FROM Livros l";
		Query query = em.createQuery(jpql, Livros.class);
		@SuppressWarnings("unchecked")
		List<Livros> listAll = query.getResultList();
		return listAll;
	}

	public Livros updateLivro(Livros livro) {
		this.em.getTransaction().begin();
		this.em.merge(livro);
		this.em.getTransaction().commit();
		return livro;
	}
}
