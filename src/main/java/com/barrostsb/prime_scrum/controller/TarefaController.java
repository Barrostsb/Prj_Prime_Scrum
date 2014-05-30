package com.barrostsb.prime_scrum.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.primefaces.event.RateEvent;
import org.primefaces.event.TabChangeEvent;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroTarefas;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Desenvolvedor_tarefa;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.Tarefa;
import com.barrostsb.prime_scrum.repository.Projetos;
import com.barrostsb.prime_scrum.repository.Tarefas;
import com.barrotsb.prime_scrum.facesUtils.FacesUtil;

@ManagedBean(name = "tarefaController")
@ViewScoped
public class TarefaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Tarefa tarefa = new Tarefa();
	private List<Tarefa> tarefasBuscadas = null;
	private List<Desenvolvedor_tarefa> tarefaDevBuscadas = null;
	private Tarefa tarefaSelecionada;
	private int idTarefaSelecionada;

	public int getIdDevTarefaSel(){
		int idDev = 11;
		 
		return idDev;
	}
	
	
	public TarefaController() {  
		tarefasBuscadas = new ArrayList<Tarefa>();
	} 

	private List<Projeto> todosProjetos = null;

	EntityManager manager = JpaUtils.getEntityManager();

	public List<Projeto> getTodosProjetos() {
		return todosProjetos;
	}

	public void setTodosProjetos(List<Projeto> todosProjetos) {
		this.todosProjetos = todosProjetos;
	}



	public void prepararCadastro() {
		EntityManager manager = JpaUtils.getEntityManager();
		try {
			Projetos projetos = new Projetos(manager);
			this.todosProjetos = projetos.todos();
		} finally {
			manager.close();
		}
	}

	public void setProj(ActionEvent event){
		tarefa.setProjeto((Projeto)event.getComponent().getAttributes().get("projeto"));
	}

	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		tarefa.setProjeto(getProjetoSelecionado());
		try {
			trx.begin();
			CadastroTarefas cadastro = new CadastroTarefas(new Tarefas(manager));
			cadastro.salvar(this.tarefa);
			this.tarefa = new Tarefa();			
			context.addMessage(null, new FacesMessage("Tarefa salva com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		new TaskBoardController();
	}

	public void alterar(Tarefa tarefa) {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroTarefas cadastro = new CadastroTarefas(new Tarefas(manager));
			cadastro.alterar(tarefa);
			tarefa = new Tarefa();			
			context.addMessage(null, new FacesMessage("Tarefa alterada com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}

	public void deletar(ActionEvent event) {
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		Tarefa tarefaSelecionada = (Tarefa) FacesUtil.getActionAttribute(event, "tarefaSelecionada");

		try {
			trx.begin();
			CadastroTarefas cadastro = new CadastroTarefas(new Tarefas(manager));
			cadastro.deletar(tarefaSelecionada);
			context.addMessage(null, new FacesMessage("Tarefa excluído com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} 
	}

	public void buscarTodasTarefa() {

		Tarefas tarefas = new Tarefas(manager);
		tarefas.todos();
	}


	public String clear(){
		tarefa = new Tarefa();
		return "/restrict/CadastrarProjeto.jsf"; 
	}


	public List<Tarefa> getTodasTarefas() {
		EntityManager maneger = JpaUtils.getEntityManager();
		tarefasBuscadas = maneger.createQuery("FROM Tarefa", Tarefa.class).getResultList();
		return tarefasBuscadas;
	}

	public List<Tarefa> getTarefaPorProjeto() {
		EntityManager maneger = JpaUtils.getEntityManager();
		tarefasBuscadas = maneger.createQuery("FROM Tarefa where id_projeto = :id_proj ", Tarefa.class)
				.setParameter("id_proj", getProjetoSelecionado().getId_projeto())
				.getResultList();
		//System.out.println("Tarefa Selecionada!>>>>>>>>>>>  "+ tarefaSelecionada.getNome() + "     "+ tarefaSelecionada.getId_tarefa());
		return tarefasBuscadas;
	}
	
	public List<Desenvolvedor_tarefa> getDadosTarefaDev() {
		EntityManager manager = JpaUtils.getEntityManager();
//		FacesContext context = FacesContext.getCurrentInstance();
//		Tarefa tarefaSelecionada = (Tarefa) FacesUtil.getActionAttribute(event, "tarefaSelecionada");
//		Session session = (Session) manager.getDelegate();  
//		Criteria criteria = session.createCriteria(Desenvolvedor_tarefa.class);  
//		tarefaDevBuscadas = criteria.list(); 
		
//		TypedQuery<Desenvolvedor_tarefa> query = manager.createQuery("Desenvolvedor_tarefa where id_tarefa = :tarefa = " + 3,//tarefaSelecionada.getId_tarefa(),
//		Desenvolvedor_tarefa.class);
//		tarefaDevBuscadas = query.getResultList();
		
//		EntityManager manager = JpaUtils.getEntityManager();
		tarefaDevBuscadas = manager.createQuery("FROM desenvolvedor_tarefa where id_tarefa = "+ idTarefaSelecionada, Desenvolvedor_tarefa.class)
			//	.setParameter("tarefa",/*tarefaSelecionada.getId_tarefa()*/3)
				.getResultList();
		return tarefaDevBuscadas;
	}

	private Projeto getProjetoSelecionado() {
		Projeto projeto;
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		projeto = (Projeto) session.getAttribute("projetoSelecionado");
		return projeto;
	}

	public Tarefa getTarefaSelecionada() {
//		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpSession session = (HttpSession) request.getSession();
//		tarefaSelecionada = (Tarefa) session.getAttribute("tarefaSelecionada");
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
//		session.setAttribute("tarefaSelecionada", tarefaSelecionada);
		this.tarefaSelecionada = tarefaSelecionada;
		idTarefaSelecionada = this.tarefaSelecionada.getId_tarefa();
	}

	public void setTodasTarefas(List<Tarefa> tarefaBuscadas) {
		this.tarefasBuscadas = tarefaBuscadas;
	}

	public Tarefa getTarefa() {

		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void atualizarDados(){
//		Tarefa tarefaSelecionada = (Tarefa) FacesUtil.getActionAttribute(event, "tarefaAtualizada");
//		Integer tarefaSelecionada = (Integer) FacesUtil.getActionAttribute(event, "tarefaAtualizada");
//		tarefaSelecionada.setPrioridade(5);
		alterar(tarefaSelecionada);
		System.out.println("prioridadeAlterada  " + tarefaSelecionada.getPrioridade());
//		System.out.println("prioridade  "+ prioridade);
	}
}

