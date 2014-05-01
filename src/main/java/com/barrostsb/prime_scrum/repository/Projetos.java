package com.barrostsb.prime_scrum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.criterion.Restrictions;

import com.barrostsb.prime_scrum.model.Desenvolvedor;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;


public class Projetos {
	
	private EntityManager manager;
	
	public void adicionar(Projeto projeto) {
		this.manager.persist(projeto);
	}
	
	public void update(Projeto projeto){
		this.manager.merge(projeto);
	}
	
	public void delete(Projeto projeto){
		this.manager.remove(projeto);
	}
	
	public Projetos(EntityManager manager) {
		this.manager = manager;
	}
	
	public Projeto porId(int id) {
		return manager.find(Projeto.class, id);
	}
	
	public List<Projeto> todos() {
		TypedQuery<Projeto> query = manager.createQuery("FROM Projeto", Projeto.class);
		return query.getResultList();
	}
	
	public List<Projeto> projetosPorSM(int id) {
		TypedQuery<Projeto> query = manager.createQuery("from Projeto  where scrumMaster = " + id,
														Projeto.class);
		return query.getResultList();
	}
	
	//TODO
	public List<Projeto> projetosPorDev(int id) {
		Desenvolvedor desenvolvedor = manager.find(Desenvolvedor.class, id);
		List< Projeto > projetos = desenvolvedor.getListaProjetos();
		return projetos;
		
//		List<Integer> id_projeto  = manager.createNativeQuery("select projeto_id from desenvolvedor_projeto where desenvolvedor_id = " +id).getResultList(); 
//		List<Projeto> projetos = null;
//		for (int idProj : id_projeto){
//			Projeto projeto = manager.find(Projeto.class, idProj);
//			projetos.add(projeto);
//		}
//		
//		return projetos;
	}
	
	
	
	public List<Projeto> projetosPorCliente(int id) {
		TypedQuery<Projeto> query = manager.createQuery("from Projeto  where cliente = " + id,
				Projeto.class);
		return query.getResultList();
	}
}


