package com.barrostsb.prime_scrum.JpaUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.sound.midi.ControllerEventListener;

import com.barrostsb.prime_scrum.controller.ScrumMasterController;
import com.barrostsb.prime_scrum.dao.ScrumMasterDAO;
import com.barrostsb.prime_scrum.model.ScrumMaster;

public class teste {
	
	
	public static void main(String[] args) {
		ScrumMasterController controller = new ScrumMasterController();
		controller.insert();
		;
	}
	
	private static void salvarScrumMaster(ScrumMaster scrumMaster) {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = manager.getTransaction();
			tx.begin();
	
			manager.persist(scrumMaster);
	
			tx.commit();
	
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
	
		} finally {
			manager.close();
		}
	
		System.out.println("ScrumMaster foi salvo e seu id e' " + scrumMaster.getId_pessoa());
	}

	private static void buscarScrumMaster(int id) {
		ScrumMaster scrumMasterBuscado = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		
		try {
			scrumMasterBuscado = maneger.find(ScrumMaster.class, id);
		} finally {
			maneger.close();
		}
	
		System.out.println("Busquei ScrumMaster pelo id " + id + " e achei: " + scrumMasterBuscado);
	}
	
	public static void buscarTodosScrumMasters() {
		List<ScrumMaster> scrumMastersBuscados = null;
	
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			scrumMastersBuscados = maneger.createQuery("FROM Produto").getResultList();
		} finally {
			maneger.close();
		}
	
		System.out.println("Scrum Masters buscados: " + scrumMastersBuscados);
	}
}
