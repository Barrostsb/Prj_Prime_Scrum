package com.barrostsb.prime_scrum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.ScrumMaster;
import com.barrostsb.prime_scrum.model.Tarefa;

public class Tarefas {
	
	private EntityManager manager;

	public void adicionar(Tarefa tarefa) {
		this.manager.persist(tarefa);
	}
	
	public void alterar(Tarefa tarefa) {
		this.manager.merge(tarefa);
	}
	
	public void deletar(Tarefa tarefa) {
		this.manager.remove(manager.find(Tarefa.class, tarefa.getId_tarefa()));
	}
	
	public Tarefas(EntityManager manager) {
		this.manager = manager;
	}

	public List<Tarefa> todos() {
		TypedQuery<Tarefa> query = manager.createQuery("From Tarefa ", Tarefa.class);
//		String jpql = "select * from tarefa where tarefa.id_projeto = 1";
//		Query query = manager . createQuery ( jpql );
//		System.out.println(query);
		return query.getResultList();
	}
}
