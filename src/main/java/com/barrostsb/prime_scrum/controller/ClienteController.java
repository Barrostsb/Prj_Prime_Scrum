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
import com.barrostsb.prime_scrum.business.CadastroDevTarefa;
import com.barrostsb.prime_scrum.business.CadastroPessoas;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Cliente;
import com.barrostsb.prime_scrum.model.Desenvolvedor;
import com.barrostsb.prime_scrum.model.Desenvolvedor_tarefa;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.ScrumMaster;
import com.barrostsb.prime_scrum.repository.DevTarefas;
import com.barrostsb.prime_scrum.repository.Pessoas;
import com.barrotsb.prime_scrum.facesUtils.FacesUtil;

@ManagedBean(name = "clienteController")
@ViewScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa cliente = new Cliente();
	private Pessoa clienteSelecionado;

	public Pessoa getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Pessoa clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
				
		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			cliente.setPermissao("ROLE_CLIENTE");
			cadastro.salvar(this.cliente);
			this.cliente = new Pessoa();			
			context.addMessage(null, new FacesMessage("Cliente salvo com sucesso!"));
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
		Pessoa clienteSelecionado = (Pessoa) FacesUtil.getActionAttribute(event, "clienteSelecionado");
		
		
		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			cadastro.deletar(clienteSelecionado);
			context.addMessage(null, new FacesMessage("Cliente excluído com sucesso!"));
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
		alterar(clienteSelecionado);
//		System.out.println("prioridade  "+ prioridade);
	}
	
	public void alterar(Pessoa cliente) {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroPessoas cadastro = new CadastroPessoas(new Pessoas(manager));
			cadastro.alterar(cliente);
			cliente = new Pessoa();			
			context.addMessage(null, new FacesMessage("Cliente alterada com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String clear(){
		cliente = new Pessoa();
		return "/restrict/CadastrarCliente.jsf"; 
	}
	
	public Pessoa getCliente() {
		return cliente;
	}
	
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
}