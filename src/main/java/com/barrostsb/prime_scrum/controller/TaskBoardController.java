package com.barrostsb.prime_scrum.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.DragDropEvent;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.Tarefa;

@ManagedBean (name = "taskBoardController")
@ViewScoped
public class TaskBoardController {

	private List<Tarefa> tarefasBuscadas = null;
	private List<Tarefa> tarefasTodo = null;
	private List<Tarefa> tarefasInprocess = null;
	private List<Tarefa> tarefasDone = null;
	private TarefaController tc = new TarefaController();

	public TaskBoardController(){
		tarefasBuscadas = new ArrayList<Tarefa>();
		createtaskBoard();
	}

	private void createtaskBoard() {
		tarefasTodo = new ArrayList<Tarefa>();
		tarefasInprocess = new ArrayList<Tarefa>();  
		tarefasDone= new ArrayList<Tarefa>();  
		List<Tarefa> tarefas = getTarefaPorProjeto();

		for(Tarefa tarefa : tarefas) {
			String op = tarefa.getTskBrdDesc();

			switch (op) {
			case "todo":
				tarefasTodo.add(tarefa);
				break;

			case "inprocess":
				tarefasInprocess.add(tarefa);
				break;

			case "done":
				tarefasDone.add(tarefa);
				break;

			default:
				break;
			}
			System.out.println(op);
		}
		System.out.println("TODO "+ tarefasTodo );
		System.out.println("IP "+ tarefasInprocess );
		System.out.println("DOne "+ tarefasDone );
	}

	public void onDropTodo(DragDropEvent event) {
		Tarefa tarefa = (Tarefa) event.getData();

		tarefa.setTskBrdDesc("todo");
		tarefa.setDataTermino(null);
		tc.alterar(tarefa);
		createtaskBoard();
	}

	public void onDropDoing(DragDropEvent event) {
		Tarefa tarefa = (Tarefa) event.getData();
		tarefa.setTskBrdDesc("inprocess");
		tarefa.setDataTermino(null);
		tc.alterar(tarefa);
		createtaskBoard();

	}

	public void onDropDone(DragDropEvent event) {
		Tarefa tarefa = (Tarefa) event.getData();
		tarefa.setTskBrdDesc("done");
		tarefa.setDataTermino(new Date());
		tc.alterar(tarefa);
		createtaskBoard();
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
		return tarefasBuscadas;
	}

	private Projeto getProjetoSelecionado() {
		Projeto projeto;
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		projeto = (Projeto) session.getAttribute("projetoSelecionado");
		return projeto;
	}

	public List<Tarefa> getTarefasBuscadas() {
		return tarefasBuscadas;
	}

	public void setTarefasBuscadas(List<Tarefa> tarefasBuscadas) {
		this.tarefasBuscadas = tarefasBuscadas;
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
}
