package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroPessoas;
import com.barrostsb.prime_scrum.business.CadastroProjetos;
import com.barrostsb.prime_scrum.business.CadastroTarefas;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Desenvolvedor;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.Tarefa;
import com.barrostsb.prime_scrum.repository.Pessoas;
import com.barrostsb.prime_scrum.repository.Projetos;
import com.barrostsb.prime_scrum.repository.Tarefas;
import com.barrotsb.prime_scrum.facesUtils.FacesUtil;

@ManagedBean(name = "desenvolvedorController")
@ViewScoped
public class DesenvolvedorController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa desenvolvedor = new Desenvolvedor();
	private Pessoa devSelecionado;

	public Pessoa getDevSelecionado() {
		return devSelecionado;
	}

	public void setDevSelecionado(Pessoa devSelecionado) {
//		idTarefaSelecionada = this.tarefaSelecionada.getId_tarefa();
		this.devSelecionado = devSelecionado;
	}

	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			desenvolvedor.setPermissao("ROLE_DEV");
			cadastro.salvar(this.desenvolvedor);
			this.desenvolvedor = new Desenvolvedor();
			context.addMessage(null, new FacesMessage("Desenvolvedor salvo com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void deletar(ActionEvent event) {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		Pessoa desenvolvedorSelecionado = (Pessoa) FacesUtil.getActionAttribute(event, "desenvolvedorSelecionado");
		
		
		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			cadastro.deletarDev(desenvolvedorSelecionado);
			context.addMessage(null, new FacesMessage("Desenvolvedor excluído com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} 
	}
	
	public void atualizarDados(){
//		Tarefa tarefaSelecionada = (Tarefa) FacesUtil.getActionAttribute(event, "tarefaAtualizada");
//		Integer tarefaSelecionada = (Integer) FacesUtil.getActionAttribute(event, "tarefaAtualizada");
//		tarefaSelecionada.setPrioridade(5);
		alterar(devSelecionado);
//		System.out.println("prioridade  "+ prioridade);
	}
	
	public void alterar(Pessoa desenvolvedor) {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			cadastro.alterar(desenvolvedor);
			desenvolvedor = new Pessoa();			
			context.addMessage(null, new FacesMessage("Desenvolvedor alterada com sucesso!"));
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