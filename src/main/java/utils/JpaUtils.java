package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	private static EntityManagerFactory factory;
	private static EntityManager manager;

	public static EntityManagerFactory getFactory() {
		if (factory == null || !factory.isOpen())
			factory = Persistence.createEntityManagerFactory("SOF3011_ASSIGNMENT");
		return factory;
	}

	public static EntityManager getManager() {
		if (manager == null || !manager.isOpen())
			manager = getFactory().createEntityManager();
		
		return manager;
	}
}
