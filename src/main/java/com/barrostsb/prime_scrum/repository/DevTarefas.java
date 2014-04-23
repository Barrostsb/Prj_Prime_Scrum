package com.barrostsb.prime_scrum.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.barrostsb.prime_scrum.model.Desenvolvedor_tarefa;
import com.barrostsb.prime_scrum.model.Projeto;

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
	
//	public List<Desenvolvedor_tarefa> dadosDeTarefaSelecionada(int id) {
//		TypedQuery<Desenvolvedor_tarefa> query = manager.createQuery("from Projeto  where desenvolvedor = " + id,
//				Projeto.class);
//		return query.getResultList();
//	}

}
