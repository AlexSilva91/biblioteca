package model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import connectionFactory.ConnectionFactory;
import model.entities.Usuario;

public class UsuarioService {
	private final EntityManager em = ConnectionFactory.getConnection();

	public Usuario saveUsuario(Usuario usuario) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(usuario);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.getMessage();
		}
		return usuario;
	}

	public Usuario findById(Integer id) {
		return this.em.find(Usuario.class, id);
	}

	public List<Usuario> listUser() {
		String jpql = "from Usuario c";
		Query query = em.createQuery(jpql, Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> lisUser = ((List<Usuario>) query.getResultList());
		return lisUser;
	}

	public Usuario updateUser(Usuario usuario) {
		this.em.getTransaction().begin();
		this.em.merge(usuario);
		this.em.getTransaction().commit();
		return usuario;
	}
}
