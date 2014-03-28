package com.barrostsb.prime_scrum.business;

import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Tarefa;
import com.barrostsb.prime_scrum.repository.Tarefas;

public class CadastroTarefas {
	private Tarefas tarefas;
	
	public CadastroTarefas(Tarefas tarefas) {
		this.tarefas = tarefas;
	}
	
	public void salvar(Tarefa tarefa) throws BusinessException {
		this.tarefas.adicionar(tarefa);
	}
	
	public void alterar(Tarefa tarefa) throws BusinessException {
		this.tarefas.alterar(tarefa);
	}
}
