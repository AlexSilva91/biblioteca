package connectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");

	public static EntityManager getConnection() {
		return emf.createEntityManager();
	}
}
