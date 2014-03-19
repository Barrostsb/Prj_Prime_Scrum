package com.barrostsb.prime_scrum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.barrostsb.prime_scrum.model.Projeto;

public class Projetos {
	
	private EntityManager manager;
	
	public void adicionar(Projeto projeto) {
		this.manager.persist(projeto);
	}
	
	public Projetos(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Projeto> todos() {
		TypedQuery<Projeto> query = manager.createQuery("from Lancamento", Projeto.class);
		
		return query.getResultList();
	}
}
