package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.primefaces.event.DragDropEvent;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroProjetos;
import com.barrostsb.prime_scrum.business.CadastroTarefas;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.Tarefa;
import com.barrostsb.prime_scrum.repository.Projetos;
import com.barrostsb.prime_scrum.repository.Tarefas;

@ManagedBean(name = "tarefaController")
@ViewScoped
public class TarefaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Tarefa tarefa = new Tarefa();
	private List<Tarefa> tarefasBuscadas = null;
	private List<Tarefa> tarefasTodo = null;
	private List<Tarefa> tarefasInprocess = null;
	private List<Tarefa> tarefasDone = null;
	
	
    public TarefaController() {  
    	tarefasBuscadas = new ArrayList<Tarefa>();
    	getTodasTarefas();
    	tarefasInprocess = new ArrayList<Tarefa>();  
  
    } 
    
	public List<Tarefa> getTarefasTodo() {
		return tarefasTodo;
	}

	public void setTarefasTodo(List<Tarefa> tarefasTodo) {
		this.tarefasTodo = tarefasTodo;
	}

	public List<Tarefa> getTarefasInprocess() {
		return tarefasInprocess;
	}

	public void setTarefasInprocess(List<Tarefa> tarefasInprocess) {
		this.tarefasInprocess = tarefasInprocess;
	}

	public List<Tarefa> getTarefasDone() {
		return tarefasDone;
	}

	public void setTarefasDone(List<Tarefa> tarefasDone) {
		this.tarefasDone = tarefasDone;
	}

	private List<Projeto> todosProjetos = null;

	EntityManager manager = JpaUtils.getEntityManager();

	public List<Projeto> getTodosProjetos() {
		return todosProjetos;
	}

	public void setTodosProjetos(List<Projeto> todosProjetos) {
		this.todosProjetos = todosProjetos;
	}

	private Tarefa tarefaSelecionada;


	public void prepararCadastro() {
		EntityManager manager = JpaUtils.getEntityManager();
		try {
			Projetos projetos = new Projetos(manager);
			this.todosProjetos = projetos.todos();
		} finally {
			manager.close();
		}
	}

	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroTarefas cadastro = new CadastroTarefas(new Tarefas(manager));
			cadastro.salvar(this.tarefa);
			this.tarefa = new Tarefa();			
			context.addMessage(null, new FacesMessage("Tarefa salvo com sucesso!"));
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

//		EntityManager manager = JpaUtils.getEntityManager();
//		TypedQuery<Tarefa> query = manager.createQuery("FROM Tarefa", Tarefa.class);
//		this.tarefasBuscadas = query.getResultList();
//		manager.close();

		//		EntityManager maneger = JpaUtils.getEntityManager();
		//		try {
		//			projetosBuscados = maneger.createQuery("FROM Projeto").getResultList();
		//		} finally {
		//			maneger.close();
		//		}
	}
	
    public void onTaskDrop(DragDropEvent ddEvent) {  
        Tarefa car = ((Tarefa) ddEvent.getData());  
  
        tarefasInprocess.add(car);  
        tarefasBuscadas.remove(car);  
    } 


	public String clear(){
		tarefa = new Tarefa();
		return "/restrict/CadastrarProjeto.jsf"; 
	}


	public List<Tarefa> getTodasTarefas() {
		EntityManager maneger = JpaUtils.getEntityManager();
		try {
			tarefasBuscadas = maneger.createQuery("FROM Tarefa").getResultList();
		} finally {
			maneger.close();
		}
		return tarefasBuscadas;
	}

	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
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

	public Tarefa getTarefaSelecionado() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionado(Tarefa tarefaSelecionado) {
		this.tarefaSelecionada = tarefaSelecionado;
	}

}

