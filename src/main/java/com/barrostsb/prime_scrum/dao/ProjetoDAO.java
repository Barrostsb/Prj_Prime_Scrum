package com.barrostsb.prime_scrum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import com.barrostsb.prime_scrum.jpautils.JpaUtils;
import com.barrostsb.prime_scrum.model.Projeto;

public class ProjetoDAO {
	
	private static void salvarProjeto(Projeto projeto) {
		EntityManager manager = JpaUtils. getEntityManager();
		EntityTransaction tx = null;
				
		try {
			tx = manager.getTransaction();
			tx.begin();
	
			manager.persist(projeto);
	
			tx.commit();
	
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
	
		} finally {
			manager.close();
		}
	
		System.out.println("Projeto foi salvo e seu id e' " + projeto.getId_projeto());
	}

	private static void buscarProjeto(int id) {
		Projeto projetoBuscado = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		
		try {
			projetoBuscado = maneger.find(Projeto.class, id);
		} finally {
			maneger.close();
		}
	
		System.out.println("Busquei produto pelo id " + id + " e achei: " + projetoBuscado);
	}
	
	public static void buscarTodosProjetos() {
		List<Projeto> projetosBuscados = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			projetosBuscados = maneger.createQuery("FROM Produto").getResultList();
		} finally {
			maneger.close();
		}
	
		System.out.println("Projetos buscados: " + projetosBuscados);
	}
}
