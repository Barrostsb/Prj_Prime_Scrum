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

@ManagedBean
@ViewScoped
public class TaskBoardController {
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

	private List<Tarefa> tarefasBuscadas = null;
	private List<Tarefa> tarefasTodo = null;
	private List<Tarefa> tarefasInprocess = null;
	private List<Tarefa> tarefasDone = null;
	 TarefaController tc = new TarefaController();
	
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
	       
//	        if (tarea.getDone() == '1') {
//	            log.log(Level.INFO, "DROP ON TODO:{0} {1}", new Object[]{tarea.getDone(), tarea.getDuracion()});
//	            tarea.setDone('0');
	            
		 	   tarefa.setTskBrdDesc("todo");
		 	   tarefa.setDataTermino(null);
		 	  tc.alterar(tarefa);
		 	   createtaskBoard();
//	            tarefasBuscadas.remove(tarea);
//	            tarefasTodo.add(tarea);
	            
	            System.out.println("ID" + tarefa.getNome());
	            System.out.println("TODO "+ tarefasTodo );
	            System.out.println("IP "+ tarefasInprocess );
	            System.out.println("DOne "+ tarefasDone );
//	        }
	    }

	    public void onDropDoing(DragDropEvent event) {
	    	Tarefa tarefa = (Tarefa) event.getData();
//	        if (tarea.getDone() == '0') {
//	            log.log(Level.INFO, "DROP ON DOING:{0} {1}", new Object[]{tarea.getDone(), tarea.getDuracion()});
//	            tarea.setDone('1');
//	            //TODO set usuario logueado
//	            
	    	   tarefa.setTskBrdDesc("inprocess");
	    	   tarefa.setDataTermino(null);
	    	   tc.alterar(tarefa);
	    	   createtaskBoard();
//	    	   tarefasBuscadas.remove(tarea);
//	           tarefasInprocess.add(tarea);
	           System.out.println("ID" + tarefa.getNome());
	           System.out.println("TODO "+ tarefasTodo );
	           System.out.println("IP "+ tarefasInprocess );
	           System.out.println("DOne "+ tarefasDone );
	           
//	        }
	    }

	    public void onDropDone(DragDropEvent event) {
	    	Tarefa tarefa = (Tarefa) event.getData();
//	      if (tarea.getDone() == '0') {
//	          log.log(Level.INFO, "DROP ON DOING:{0} {1}", new Object[]{tarea.getDone(), tarea.getDuracion()});
//	          tarea.setDone('1');
//	          //TODO set usuario logueado
//	          
	  	   tarefa.setTskBrdDesc("done");
	  	   tarefa.setDataTermino(new Date());
	  	   tc.alterar(tarefa);
	  	   createtaskBoard();
//	  	   tarefasBuscadas.remove(tarea);
//	         tarefasInprocess.add(tarea);
	         System.out.println("ID" + tarefa.getNome());
	         System.out.println("TODO "+ tarefasTodo );
	         System.out.println("IP "+ tarefasInprocess );
	         System.out.println("DOne "+ tarefasDone );
//	        }
	    }    
	    


		public List<Tarefa> getTodasTarefas() {
			EntityManager maneger = JpaUtils.getEntityManager();
//			TypedQuery<Tarefa> query = manager.createQuery("From Tarefa ", Tarefa.class);

//			try {
//				tarefasBuscadas = maneger.createQuery("FROM Tarefa where id_projeto = :projeto").setParameter("projeto", 1).getResultList();
				tarefasBuscadas = maneger.createQuery("FROM Tarefa", Tarefa.class).getResultList();
//			} finally {
//				maneger.close();
//			}
//			
//			String jpql = "select c from tarefa c";
//			tarefasBuscadas = manager.createQuery( jpql ).getResultList();
			return tarefasBuscadas;
//			return query.getResultList();
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


}
