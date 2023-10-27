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
			System.out.println(e.getMessage());
		}
		return emprestimo;
	}

	public Emprestimo findByIdUser(Integer id) {
		Query q = em.createQuery("SELECT e FROM Emprestimo as e where e.usuario.cpf =: id", Emprestimo.class)
				.setParameter("id", id);
		Emprestimo emprestimo = (Emprestimo) q.getSingleResult();
		return emprestimo;
	}

	@SuppressWarnings("unchecked")
	public List<Emprestimo> listAllFindByIdUser(Integer id) {
		Query q = em.createQuery("SELECT e FROM Emprestimo as e where e.usuario.cpf =: id", Emprestimo.class)
				.setParameter("id", id);
		List<Emprestimo> listAll = q.getResultList();
		return listAll;
	}

	public Emprestimo findById(Integer id) {
		return this.em.find(Emprestimo.class, id);
	}

	public Emprestimo updateEmprestimo(Emprestimo emprestimo) {
		this.em.getTransaction().begin();
		this.em.merge(emprestimo);
		this.em.getTransaction().commit();
		return emprestimo;
	}
}
