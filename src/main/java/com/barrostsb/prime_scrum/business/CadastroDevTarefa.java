package com.barrostsb.prime_scrum.business;

import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Desenvolvedor_tarefa;
import com.barrostsb.prime_scrum.repository.DevTarefas;

public class CadastroDevTarefa {
	private DevTarefas tarefas;
	
	public CadastroDevTarefa(DevTarefas tarefas) {
		this.tarefas = tarefas;
	}
	
	public void salvar(Desenvolvedor_tarefa tarefa) throws BusinessException {
		this.tarefas.adicionar(tarefa);
	}
	
	public void alterar(Desenvolvedor_tarefa tarefa) throws BusinessException {
		this.tarefas.alterar(tarefa);
	}
}
