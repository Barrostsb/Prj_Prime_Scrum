package com.barrostsb.prime_scrum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.ScrumMaster;

public class Pessoas {
	
	private EntityManager manager;

	public void adicionar(Pessoa pessoa) {
		this.manager.persist(pessoa);
	}
	
	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	public List<Pessoa> todos() {
		TypedQuery<Pessoa> query = manager.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}
	
	public List<Pessoa> todosScrumMasters() {
		TypedQuery<Pessoa> query = manager.createQuery("from Pessoa , ScrumMaster  where id_pessoa = id_scrumMaster",
														Pessoa.class);
		return query.getResultList();
	}
	
	public List<Pessoa> todosDesenvolvedores() {
		TypedQuery<Pessoa> query = manager.createQuery("from Lancamento", Pessoa.class);
		return query.getResultList();
	}
}
