package model.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import connectionFactory.ConnectionFactory;
import model.entities.Endereco;
import model.entities.Usuario;

public class EnderecoService {
	private final EntityManager em = ConnectionFactory.getConnection();

	public Endereco saveEndereco(Endereco endereco) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(endereco);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
		}
		return endereco;
	}

	public Endereco findById(long id) {
		return this.em.find(Endereco.class, id);
	}

	public Endereco findByidUser(Long idUser) {
		Endereco endereco = new Endereco();
		try {
			String jpql = "SELECT e FROM Endereco as e where e.idUser =: idUser";
			Query query = em.createQuery(jpql, Endereco.class).setParameter("idUser", idUser);
			endereco = (Endereco) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return endereco;
	}

	public Endereco updateEndereco(Endereco endereco) {
		this.em.getTransaction().begin();
		this.em.detach(endereco);
		this.em.merge(endereco);
		this.em.getTransaction().commit();
		return endereco;
	}
}
