package com.barrostsb.prime_scrum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;




import com.barrostsb.prime_scrum.EntitiesPKs.JpaUtils;
import com.barrostsb.prime_scrum.model.Sprint;

public class SprintDAO {
		
	private static void salvarHistoria(Sprint sprint) {
		EntityManager manager = JpaUtils. getEntityManager();
		EntityTransaction tx = null;
				
		try {
			tx = manager.getTransaction();
			tx.begin();
	
			manager.persist(sprint);
	
			tx.commit();
	
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
	
		} finally {
			manager.close();
		}
	
		//System.out.println("Sprint foi salvo e seu id e' " + sprint.getSprintpk().getId_sprint());
	}

	private static void buscarSprint(int id) {
		Sprint sprintBuscado = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		
		try {
			sprintBuscado = maneger.find(Sprint.class, id);
		} finally {
			maneger.close();
		}
	
		System.out.println("Busquei Sprint pelo id " + id + " e achei: " + sprintBuscado);
	}
	
	public static void buscarTodosSprint() {
		List<Sprint> sprintBuscados = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			sprintBuscados = maneger.createQuery("FROM Produto").getResultList();
		} finally {
			maneger.close();
		}
	
		System.out.println("Sprint buscados: " + sprintBuscados);
	}
}
