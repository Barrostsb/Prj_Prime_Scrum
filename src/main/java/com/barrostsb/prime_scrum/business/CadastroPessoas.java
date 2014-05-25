package com.barrostsb.prime_scrum.business;

import javax.persistence.EntityManager;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.ScrumMaster;
import com.barrostsb.prime_scrum.repository.Pessoas;

public class CadastroPessoas {
	private Pessoas pessoas;
	
	public CadastroPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}
	
	public void salvar(Pessoa pessoa) throws BusinessException {
		this.pessoas.adicionar(pessoa);
	}
	
	public void alterar(Pessoa pessoa) throws BusinessException {
		this.pessoas.alterar(pessoa);
	}
	
	public void deletar(Pessoa pessoa) throws BusinessException {
		this.pessoas.delete(pessoa);
	}
	public void deletarCliente(Pessoa pessoa) throws BusinessException {
		this.pessoas.deleteCliente(pessoa);
	}
	public void deletarDev(Pessoa pessoa) throws BusinessException {
		this.pessoas.deleteDev(pessoa);
	}
}
