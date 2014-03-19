package com.barrostsb.prime_scrum.business;

import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.repository.Projetos;

public class CadastroProjetos {
	private Projetos projetos;
	
	public CadastroProjetos(Projetos projetos) {
		this.projetos = projetos;
	}
	
	public void salvar(Projeto projeto) throws BusinessException {
		this.projetos.adicionar(projeto);
	}

}
