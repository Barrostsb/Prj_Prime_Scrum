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
import com.barrostsb.prime_scrum.model.Desenvolvedor;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.repository.Pessoas;

@ManagedBean(name = "desenvolvedorController")
@ViewScoped
public class DesenvolvedorController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa desenvolvedor = new Desenvolvedor();

	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			cadastro.salvar(this.desenvolvedor);
			this.desenvolvedor = new Pessoa();			
			context.addMessage(null, new FacesMessage("Desenvolvedor salvo com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}

	public String clear(){
		desenvolvedor = new Pessoa();
		return "/restrict/CadastrarDesenvolvedor.jsf"; 
	}

	public Pessoa getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(Desenvolvedor dev) {
		this.desenvolvedor = dev;
	}
}