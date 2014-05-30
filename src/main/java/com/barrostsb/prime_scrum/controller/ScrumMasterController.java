package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroPessoas;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.ScrumMaster;
import com.barrostsb.prime_scrum.repository.Pessoas;

@ManagedBean(name = "scrumMasterController")
@ViewScoped
public class ScrumMasterController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa scrumMaster = new ScrumMaster();

	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
				
		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			scrumMaster.setPermissao("ROLE_ADM");

			cadastro.salvar(this.scrumMaster);
			this.scrumMaster = new Pessoa();			
			context.addMessage(null, new FacesMessage("Scrum Master salvo com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void alterar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
				
		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			scrumMaster.setPermissao("ROLE_ADM");

			cadastro.salvar(this.scrumMaster);
			this.scrumMaster = new Pessoa();			
			context.addMessage(null, new FacesMessage("Scrum Master salvo com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String clear(){
		scrumMaster = new Pessoa();
		return "/restrict/CadastrarScrumMaster.jsf"; 
	}
	
	public Pessoa getScrumMaster() {
		return scrumMaster;
	}
	
	public void setScrumMaster(ScrumMaster sm) {
		this.scrumMaster = sm;
	}
}