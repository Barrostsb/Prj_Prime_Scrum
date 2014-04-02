package com.barrostsb.prime_scrum.JpaUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sound.midi.ControllerEventListener;

import com.barrostsb.prime_scrum.controller.ProjetoController;
import com.barrostsb.prime_scrum.controller.ScrumMasterController;
import com.barrostsb.prime_scrum.controller.TarefaController;
import com.barrostsb.prime_scrum.controller.TaskBoardController;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.ScrumMaster;
import com.barrostsb.prime_scrum.model.Tarefa;
import com.barrostsb.prime_scrum.repository.Pessoas;

public class teste {
	
	
	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("Prime_ScrumPU");
//		EntityManager manager = JpaUtils.getEntityManager();
//		Pessoas p = new Pessoas(manager);
//		ProjetoController pc = new ProjetoController();
//		pc.buscarTodosProjetos();
//		List<Projeto> proj = pc.getProjetosBuscados();
//		
//	       for(Projeto model : proj) {
//	            System.out.println(model.getNome());
//	        }
	       
//			EntityManager manager = JpaUtils.getEntityManager();
//			EntityTransaction trx = manager.getTransaction();
//			trx.begin();
//			
//			Projeto p1 = new Projeto();
//			p1.setNome("Teste tarefa proj");
//			
//			Tarefa t1 = new Tarefa();
//			t1.setNome("Teste tarefa");
//			
//			
//			t1.setProjeto(p1);
//			
//			
//			manager.persist(p1);
//			manager.persist(t1);
//			
//			trx.commit();
//			
//			manager.close();
		
//		TarefaController pc1 = new TarefaController();
//		pc1.buscarTodasTarefa();
//		List<Tarefa> proj1 = pc1.getTodasTarefas();
//		
//	       for(Tarefa model : proj1) {
//	            System.out.println(model.getNome());
//	        }
	       
	    TaskBoardController pc2 = new TaskBoardController();
	   
	    
		

			

	      
//			ProjetoController pc = new ProjetoController();
//			pc.buscarTodosProjetos();
//			List<Projeto> proj = pc.getProjetosBuscados();
//			
//		       for(Projeto model : proj) {
//		            System.out.println(model.getNome());
//		        }
	       
	}
	
	
}
