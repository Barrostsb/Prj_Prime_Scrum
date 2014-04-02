package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroProjetos;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.repository.Projetos;

@ManagedBean(name = "projetoController")
@ApplicationScoped
public class ProjetoController implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private Projeto projeto = new Projeto();
	private List<Projeto> projetosBuscados = null;

	private Projeto projetoSelecionado;

	EntityManager manager = JpaUtils.getEntityManager();

	public void salvar() {
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
		} 
	}
	
	public void alterar() {
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			CadastroProjetos cadastro = new CadastroProjetos(new Projetos(manager));
			cadastro.alterar(this.projeto);
			this.projeto = new Projeto();			
			context.addMessage(null, new FacesMessage("Projeto alterado com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} 
	}

	public void buscarTodosProjetos() {
		Projetos prjs = new Projetos(manager);
		prjs.todos();

		//		EntityManager manager = JpaUtils.getEntityManager();
		//		TypedQuery<Projeto> query = manager.createQuery("from Projeto", Projeto.class);
		//		this.projetosBuscados = query.getResultList();
		//		manager.close();

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
//		EntityManager maneger = JpaUtils.getEntityManager();
//		try {
			projetosBuscados = manager.createQuery("FROM Projeto", Projeto.class).getResultList();
//			TypedQuery<Projeto> query = maneger.createQuery("from Projeto", Projeto.class);
//			projetosBuscados = query.getResultList();
////			manager.close();
//		} finally {
//			maneger.close();
//		}
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
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("projetoSelecionado", projetoSelecionado);
	}

}

