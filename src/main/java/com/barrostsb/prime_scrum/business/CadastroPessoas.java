package com.barrostsb.prime_scrum.business;

import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.repository.Pessoas;

public class CadastroPessoas {
	private Pessoas pessoas;
	
	public CadastroPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}
	
	public void salvar(Pessoa pessoa) throws BusinessException {
		this.pessoas.adicionar(pessoa);
	}
}
