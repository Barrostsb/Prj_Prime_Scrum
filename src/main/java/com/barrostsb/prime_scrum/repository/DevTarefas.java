package com.barrostsb.prime_scrum.repository;


import javax.persistence.EntityManager;

import com.barrostsb.prime_scrum.model.Desenvolvedor_tarefa;

public class DevTarefas {
	
	private EntityManager manager;

	public void adicionar(Desenvolvedor_tarefa tarefa) {
		this.manager.persist(tarefa);
	}
	
	public void alterar(Desenvolvedor_tarefa tarefa) {
		this.manager.merge(tarefa);
	}
	
	public DevTarefas(EntityManager manager) {
		this.manager = manager;
	}

}
