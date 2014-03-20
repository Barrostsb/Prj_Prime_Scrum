package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroProjetos;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.repository.Projetos;

@ManagedBean(name = "projetoController")
@ViewScoped
public class ProjetoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Projeto projeto = new Projeto();
	private List<Projeto> projetosBuscados = null;
	
	private Projeto projetoSelecionado;
	
	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			CadastroProjetos cadastro = new CadastroProjetos(new Projetos(manager));
			cadastro.salvar(this.projeto);
			this.projeto = new Projeto();			
			context.addMessage(null, new FacesMessage("Projeto salvo com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} finally {
			manager.close();
		}
	}
	
	public void buscarTodosProjetos() {
	
		EntityManager manager = JpaUtils.getEntityManager();
		TypedQuery<Projeto> query = manager.createQuery("from Projeto", Projeto.class);
		this.projetosBuscados = query.getResultList();
		manager.close();
		
//		EntityManager maneger = JpaUtils.getEntityManager();
//		try {
//			projetosBuscados = maneger.createQuery("FROM Projeto").getResultList();
//		} finally {
//			maneger.close();
//		}
	}
	
	
	public String clear(){
		projeto = new Projeto();
		return "/restrict/CadastrarProjeto.jsf"; 
	}
	
	
	public List<Projeto> getProjetosBuscados() {
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			projetosBuscados = maneger.createQuery("FROM Projeto").getResultList();
		} finally {
			maneger.close();
		}
		return projetosBuscados;
	}

	public void setProjetosBuscados(List<Projeto> projetosBuscados) {
		this.projetosBuscados = projetosBuscados;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

}

