package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.barrostsb.prime_scrum.model.Tarefa;

@ManagedBean (name = "taskBoardController")
public class TaskBoardController implements Serializable {  

	private static final long serialVersionUID = 1L;

	private DashboardModel model; 

	private TarefaController tarefaController;

	private List<Tarefa> tarefasTodo = new ArrayList<Tarefa>();;
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

	private List<Tarefa> tarefasInprocess = null;
	private List<Tarefa> tarefasDone = null;

	public TaskBoardController() {  
		model = new DefaultDashboardModel();  
		tarefaController = new TarefaController();

		DashboardColumn column1 = new DefaultDashboardColumn();  
		DashboardColumn column2 = new DefaultDashboardColumn();  
		DashboardColumn column3 = new DefaultDashboardColumn();  

		TarefaController pc1 = new TarefaController();
		pc1.buscarTodasTarefa();
		CreateBoard();

		for(Tarefa model : tarefasTodo) {
			column1.addWidget(model.getNome());
		}
		
		
		System.out.println("COLUNA 1: "+ column1.getWidgets());
		

//		column1.addWidget(0, "todo");
//		column1.addWidget("sports");  
//		column1.addWidget("finance");  
//
//		column2.addWidget(0, "inprocess");
//		column2.addWidget("lifestyle");  
//		column2.addWidget("weather");  
//
//		column3.addWidget(0, "done");
//		column3.addWidget("politics");  

		model.addColumn(column1);  
		model.addColumn(column2);  
		model.addColumn(column3);  

	} 

	public void CreateBoard(){
		TarefaController pc1 = new TarefaController();
		pc1.buscarTodasTarefa();

		tarefasTodo = pc1.getTodasTarefas();

//		for(Tarefa model : tarefasTodo) {
//			System.out.println(model.getNome());
//		}
	}

	public void handleReorder(DashboardReorderEvent event) {  
		FacesMessage message = new FacesMessage();  
		message.setSeverity(FacesMessage.SEVERITY_INFO);  
		message.setSummary("Reordered: " + event.getWidgetId());  
		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());  

		addMessage(message);  
	}  


	private void addMessage(FacesMessage message) {  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}  

	public DashboardModel getModel() {  
		return model;  
	}  
}  