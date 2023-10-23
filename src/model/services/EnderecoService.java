package model.services;

import javax.persistence.EntityManager;

import connectionFactory.ConnectionFactory;
import model.entities.Endereco;

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

	public Endereco findById(Integer id) {
		return this.em.find(Endereco.class, id);
	}
	
	public Endereco updateEndereco(Endereco endereco) {
		this.em.getTransaction().begin();
		this.em.merge(endereco);
		this.em.getTransaction().commit();
		return endereco;
	}
}
