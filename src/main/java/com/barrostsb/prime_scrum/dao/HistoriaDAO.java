package com.barrostsb.prime_scrum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import com.barrostsb.prime_scrum.jpautils.JpaUtils;
import com.barrostsb.prime_scrum.model.Historia;

public class HistoriaDAO {
	
	private static void salvarHistoria(Historia historia) {
		EntityManager manager = JpaUtils. getEntityManager();
		EntityTransaction tx = null;
				
		try {
			tx = manager.getTransaction();
			tx.begin();
	
			manager.persist(historia);
	
			tx.commit();
	
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
	
		} finally {
			manager.close();
		}
	
		//System.out.println("Historia foi salvo e seu id e' " + historia.getHistoriaPK().getId_historia());
	}

	private static void buscarHistoria(int id) {
		Historia historiaBuscado = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		
		try {
			historiaBuscado = maneger.find(Historia.class, id);
		} finally {
			maneger.close();
		}
	
		System.out.println("Busquei Historia pelo id " + id + " e achei: " + historiaBuscado);
	}
	
	public static void buscarTodoshistoria() {
		List<Historia> historiaBuscados = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			historiaBuscados = maneger.createQuery("FROM Produto").getResultList();
		} finally {
			maneger.close();
		}
	
		System.out.println("Historia buscados: " + historiaBuscados);
	}
}
