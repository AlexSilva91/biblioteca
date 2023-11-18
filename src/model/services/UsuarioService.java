package model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	public Usuario findById(long id) {
		return this.em.find(Usuario.class, id);
	}

	public Usuario findByName(String name) {
		Usuario usuario = new Usuario();
		try {
			String jpql = "SELECT u FROM Usuario as u where u.nome =: name";
			Query query = em.createQuery(jpql, Usuario.class).setParameter("name", name);
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			usuario = null;
		}
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listAllFindByRoad(String road) {
		String jpql = "SELECT u FROM Usuario as u where u.endereco.rua =: road";
		Query query = em.createQuery(jpql, Usuario.class).setParameter("road", road);
		List<Usuario> listAllFindByRoad = query.getResultList();
		return listAllFindByRoad;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listAllFindByNeighborhood(String neighborhood) {
		String jpql = "SELECT u FROM Usuario as u where u.endereco.bairro =: neighborhood";
		Query query = em.createQuery(jpql, Usuario.class).setParameter("neighborhood", neighborhood);
		List<Usuario> listAllFindByNeighborhood = query.getResultList();
		return listAllFindByNeighborhood;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listAllFindByCity(String city) {
		String jpql = "SELECT u FROM Usuario as u where u.endereco.cidade =: city";
		Query query = em.createQuery(jpql, Usuario.class).setParameter("city", city);
		List<Usuario> listAllFindByCity = query.getResultList();
		return listAllFindByCity;
	}

	public List<Usuario> listUser() {
		String jpql = "from Usuario u";
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
