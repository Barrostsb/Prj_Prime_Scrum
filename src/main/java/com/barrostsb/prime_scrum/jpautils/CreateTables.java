package com.barrostsb.prime_scrum.jpautils;

import javax.persistence.Persistence;

public class CreateTables {
//	private static EntityManagerFactory factory;
	
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("Prime_ScrumPU");
	}
	
//	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prime_ScrumPU");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().commit();
//	}
}
