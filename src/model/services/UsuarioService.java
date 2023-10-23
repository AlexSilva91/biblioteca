package model.services;

import javax.persistence.EntityManager;

import connectionFactory.ConnectionFactory;
import model.entities.Usuario;

public class UsuarioService {
	private final EntityManager em = ConnectionFactory.getConnection();
	
	public Usuario saveUsuario(Usuario usuario) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(usuario);
			this.em.getTransaction().commit();
		}catch (Exception e) {
			this.em.getTransaction().rollback();
			e.getMessage();
		}
		return usuario;
	}
	
	public Usuario findById(Integer id) {
		return this.em.find(Usuario.class, id);
	}
}
