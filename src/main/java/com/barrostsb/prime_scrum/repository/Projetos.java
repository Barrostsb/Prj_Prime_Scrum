package com.barrostsb.prime_scrum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;

public class Projetos {
	
	private EntityManager manager;
	
	public void adicionar(Projeto projeto) {
		this.manager.persist(projeto);
	}
	
	public void update(Projeto projeto){
		this.manager.merge(projeto);
	}
	
	public void delete(Projeto projeto){
		this.manager.remove(projeto);
	}
	
	public Projetos(EntityManager manager) {
		this.manager = manager;
	}
	
	public Projeto porId(int id) {
		return manager.find(Projeto.class, id);
	}
	
	public List<Projeto> todos() {
		TypedQuery<Projeto> query = manager.createQuery("FROM Projeto", Projeto.class);
		return query.getResultList();
	}
	
	public List<Projeto> projetosPorSM(int id) {
		TypedQuery<Projeto> query = manager.createQuery("from Projeto  where scrumMaster = " + id,
														Projeto.class);
		return query.getResultList();
	}
	
	//TODO
	public List<Projeto> projetosPorDev(int id) {
		TypedQuery<Projeto> query = manager.createQuery("from Projeto  where desenvolvedor = " + id,
				Projeto.class);
		return query.getResultList();
	}
	
	public List<Projeto> projetosPorCliente(int id) {
		TypedQuery<Projeto> query = manager.createQuery("from Projeto  where cliente = " + id,
				Projeto.class);
		return query.getResultList();
	}
}
