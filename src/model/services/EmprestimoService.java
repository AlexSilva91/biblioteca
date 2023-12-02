package model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import connectionFactory.ConnectionFactory;
import model.entities.Emprestimo;

public class EmprestimoService {
	private EntityManager em = ConnectionFactory.getConnection();

	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(emprestimo);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
		}
		return emprestimo;
	}

	public Emprestimo findByIdUser(long id) {
		Query q = em.createQuery("SELECT e FROM Emprestimo as e where e.usuario.cpf =: id", Emprestimo.class)
				.setParameter("id", id);
		Emprestimo emprestimo = (Emprestimo) q.getSingleResult();
		return emprestimo;
	}

	@SuppressWarnings("unchecked")
	public List<Emprestimo> listAllFindByIdUser(long id) {
		Query q = em.createQuery("SELECT e FROM Emprestimo as e where e.usuario.cpf =: id", Emprestimo.class)
				.setParameter("id", id);
		List<Emprestimo> listAll = q.getResultList();
		return listAll;
	}
	
	@SuppressWarnings("unchecked")
	public List<Emprestimo> listAllFindByTitle(String title) {
		Query q = em.createQuery("SELECT e FROM Emprestimo as e where e.titulo =: title", Emprestimo.class)
				.setParameter("title", title);
		List<Emprestimo> listAll = q.getResultList();
		return listAll;
	}

	@SuppressWarnings("unchecked")
	public List<Emprestimo> listAllFindByStatus(boolean status) {
		Query q = em.createQuery("SELECT e FROM Emprestimo as e where e.status =: status", Emprestimo.class)
				.setParameter("status", status);
		List<Emprestimo> listAll = q.getResultList();
		return listAll;
	}

	public Emprestimo findById(long id) {
		return this.em.find(Emprestimo.class, id);
	}

	public Emprestimo updateEmprestimo(Emprestimo emprestimo) {
		this.em.getTransaction().begin();
		this.em.merge(emprestimo);
		this.em.getTransaction().commit();
		return emprestimo;
	}
}
