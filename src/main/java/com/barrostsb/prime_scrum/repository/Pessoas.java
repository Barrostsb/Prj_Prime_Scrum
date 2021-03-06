package com.barrostsb.prime_scrum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.barrostsb.prime_scrum.model.Desenvolvedor;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.ScrumMaster;

public class Pessoas {
	
	private EntityManager manager;

	public void adicionar(Pessoa pessoa) {
		this.manager.persist(pessoa);
	}
	
	public void alterar(Pessoa pessoa) {
		this.manager.merge(pessoa);
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
	
	public Pessoa ScrumMasterPorId(Long id) {
		return manager.find(ScrumMaster.class, id);
	}
	
	public List<Pessoa> todosDesenvolvedores() {
		TypedQuery<Pessoa> query = manager.createQuery("from Desenvolvedor where id_pessoa = id_desenvolvedor", Pessoa.class);
		return query.getResultList();
	}
	
	public Pessoa PessoaPorId(int id) {
		return manager.find(Pessoa.class, id);
	}
	
	public Pessoa PessoaPorLogin(String login) {
		return (Pessoa) manager.createQuery("from Pessoa where login = '" + login +"'").getSingleResult();// find(Pessoa.class, login);
	}
	
	public Pessoa DesenvolvedorPorId(Long id) {
		return manager.find(Desenvolvedor.class, id);
	}

	public void delete(Pessoa pessoa){

		this.manager.remove(manager.find(Pessoa.class, pessoa.getId_pessoa()));
	}
	public void deleteCliente(Pessoa pessoa){
		List<Projeto> projetoBuscados;
		projetoBuscados = manager.createQuery("FROM Projeto where cliente = :id_cliente ", Projeto.class)
				.setParameter("id_cliente", pessoa)
				.getResultList();
		
		for (Projeto projeto : projetoBuscados){
			projeto.setCliente(null);
			manager.persist(projeto);
		}
		this.manager.remove(manager.find(Pessoa.class, pessoa.getId_pessoa()));
	}
	public void deleteDev(Pessoa pessoa){
		List<Projeto> projetoBuscados;
		projetoBuscados = manager.createQuery("FROM Projeto ", Projeto.class)
				.getResultList();
		
		for (Projeto projeto : projetoBuscados){
			projeto.getListaDesenvolvedores().remove(pessoa);
			manager.persist(projeto);
		}
		this.manager.remove(manager.find(Pessoa.class, pessoa.getId_pessoa()));
	}
}
