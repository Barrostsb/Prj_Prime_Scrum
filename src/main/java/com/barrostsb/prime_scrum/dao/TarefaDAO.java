package com.barrostsb.prime_scrum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import com.barrostsb.prime_scrum.jpautils.JpaUtils;
import com.barrostsb.prime_scrum.model.Tarefa;

public class TarefaDAO {
	
	private static void salvarTarefa(Tarefa tarefa) {
		EntityManager manager = JpaUtils. getEntityManager();
		EntityTransaction tx = null;
				
		try {
			tx = manager.getTransaction();
			tx.begin();
	
			manager.persist(tarefa);
	
			tx.commit();
	
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
	
		} finally {
			manager.close();
		}
	
		System.out.println("Historia foi salvo e seu id e' " + tarefa.getTarefapk().getId_tarefa());
	}

	private static void buscarTarefa(int id) {
		Tarefa tarefaBuscado = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		
		try {
			tarefaBuscado = maneger.find(Tarefa.class, id);
		} finally {
			maneger.close();
		}
	
		System.out.println("Busquei Tarefa pelo id " + id + " e achei: " + tarefaBuscado);
	}
	
	public static void buscarTodoshistoria() {
		List<Tarefa> tarefaBuscados = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			tarefaBuscados = maneger.createQuery("FROM Produto").getResultList();
		} finally {
			maneger.close();
		}
	
		System.out.println("Historia buscados: " + tarefaBuscados);
	}
}
