package com.barrostsb.prime_scrum.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory(
				"Prime_ScrumPU");
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
