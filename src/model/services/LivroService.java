package model.services;

import javax.persistence.EntityManager;

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
	
	public Livros findById(Integer id) {
		return this.em.find(Livros.class, id);
	}
	
	public Livros updateLivro(Livros livro) {
		this.em.getTransaction().begin();
		this.em.merge(livro);
		this.em.getTransaction().commit();
		return livro;
	}
}
