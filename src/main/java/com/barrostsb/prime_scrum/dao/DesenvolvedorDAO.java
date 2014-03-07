package com.barrostsb.prime_scrum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;

import com.barrostsb.prime_scrum.jpautils.JpaUtils;
import com.barrostsb.prime_scrum.model.Desenvolvedor;

public class DesenvolvedorDAO {
	
	private static void salvarDesenvolvedor(Desenvolvedor desenvolvedor) {
		EntityManager manager = JpaUtils. getEntityManager();
		EntityTransaction tx = null;
				
		try {
			tx = manager.getTransaction();
			tx.begin();
	
			manager.persist(desenvolvedor);
			
			
	
			tx.commit();
	
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
	
		} finally {
			manager.close();
		}
	
		System.out.println("Desenvolvedor foi salvo e seu id e' " + desenvolvedor.getId_pessoa());
	}

	private static void buscarDesenvolvedor(int id) {
		Desenvolvedor desenvolvedorBuscado = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		
		try {
			desenvolvedorBuscado = maneger.find(Desenvolvedor.class, id);
		} finally {
			maneger.close();
		}
	
		System.out.println("Busquei Desenvolvedor pelo id " + id + " e achei: " + desenvolvedorBuscado);
	}
	
	public static void buscarTodosDesenvolvedores() {
		List<Desenvolvedor> desenvolvedoresBuscados = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			desenvolvedoresBuscados = maneger.createQuery("FROM Produto").getResultList();
		} finally {
			maneger.close();
		}
	
		System.out.println("Desenvolvedores buscados: " + desenvolvedoresBuscados);
	}
}
