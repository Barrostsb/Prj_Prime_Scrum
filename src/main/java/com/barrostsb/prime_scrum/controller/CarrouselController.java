package com.barrostsb.prime_scrum.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.barrostsb.prime_scrum.imagens.ScreenShots;
import com.barrostsb.prime_scrum.imagens.Tecnologias;

@ManagedBean
public class CarrouselController {
	    
		private List<Tecnologias> tecnologias;
	  	private List<ScreenShots> images;
	     
	    @PostConstruct
	    public void init() {
	        createScreenShots();
	        createTecnologias();
	        
	    }
	 
	   
	    
	    public List<ScreenShots> getImages() {
	        return images;
	    }
	    
	    
	    public List<Tecnologias> getTecnologias() {
			return tecnologias;
		}
	    
	    public List<ScreenShots> createScreenShots(){
	    	images = new ArrayList<ScreenShots>();
	    	ScreenShots s1 = new ScreenShots();
	    	s1.setNome("1.png");
	    	s1.setTitulo("Prime Scrum");
	    	s1.setDescricao("Gerencie seus projetos com SCRUM utilizando esta incrível ferramenta!");
	    	
	    	ScreenShots s2 = new ScreenShots();
	    	s2.setNome("2.png");
	    	s2.setTitulo("Acesso rápido");
	    	s2.setDescricao("Veja o status de seu projeto rapidamente! ");
	    	
	    	ScreenShots s3 = new ScreenShots();
	    	s3.setNome("3.png");
	    	s3.setTitulo("Listagem de Stackholders");
	    	s3.setDescricao("O Prime Scrum lista para você todos os seus clientes e desenvolvedores!");
	    	
	    	ScreenShots s4 = new ScreenShots();
	    	s4.setNome("4.png");
	    	s4.setTitulo("Listagem de Projetos");
	    	s4.setDescricao("Encontre seus projetos de uma forma rápida e fácil!");
	    	
	    	ScreenShots s5 = new ScreenShots();
	    	s5.setNome("5.png");
	    	s5.setTitulo("Listagem de Tarefa");
	    	s5.setDescricao("Veja suas tarefas com um visual de bilhetes!");
	    	
	    	ScreenShots s6= new ScreenShots();
	    	s6.setNome("6.png");
	    	s6.setTitulo("Planning");
	    	s6.setDescricao("Veja e altere rapidamente suas tarefas com base nas informações de seus desenvolvedores!");
	    	
	    	ScreenShots s7 = new ScreenShots();
	    	s7.setNome("7.png");
	    	s7.setTitulo("Scrum Task Board");
	    	s7.setDescricao("O Prime Scrum possui uma task board para facilitar a visualização de suas tarefas! ");
	    	
	    	ScreenShots s8 = new ScreenShots();
	    	s8.setNome("8.png");
	    	s8.setTitulo("Burndown Chart");
	    	s8.setDescricao("O Prime Scrum possui uma burn down chart para facilitar o gerenciamento do tempo para o termini de seus projetos!");
	    	
	    	ScreenShots s9 = new ScreenShots();
	    	s9.setNome("9.png");
	    	s9.setTitulo("Ele é livre");
	    	s9.setDescricao("O Prime Scrum é livre, altere como desejar!");
	    	
	    	images.add(s1);
	    	images.add(s2);
	    	images.add(s3);
	    	images.add(s4);
	    	images.add(s5);
	    	images.add(s6);
	    	images.add(s7);
	    	images.add(s8);
	    	images.add(s9);
	    	return images;
	    }
		
		 public List<Tecnologias> createTecnologias(){
		    	tecnologias = new ArrayList<Tecnologias>();
		    	
		    	Tecnologias t1 = new Tecnologias();
		    	t1.setNome("1.png");
		    	t1.setTitulo("Linguagem java");
		    	t1.setDescricao("java 7");
		    	
		    	Tecnologias t2 = new Tecnologias();
		    	t2.setNome("2.png");
		    	t2.setTitulo("Eclipse IDE");
		    	t2.setDescricao("Eclipse IDE Kepler");
		    	
		    	Tecnologias t3 = new Tecnologias();
		    	t3.setNome("3.png");
		    	t3.setTitulo("JFS");
		    	t3.setDescricao("Java Server Faces");
		    	
		    	Tecnologias t4 = new Tecnologias();
		    	t4.setNome("4.png");
		    	t4.setTitulo("Prime Faces");
		    	t4.setDescricao("Implementação do jsf");
		    	
		    	Tecnologias t5 = new Tecnologias();
		    	t5.setNome("5.png");
		    	t5.setTitulo("Hibernate");
		    	t5.setDescricao("hibernate ");
		    	
		    	Tecnologias t6 = new Tecnologias();
		    	t6.setNome("6.png");
		    	t6.setTitulo("Mysql");
		    	t6.setDescricao("mysql oracle");
		    	
		    	Tecnologias t7 = new Tecnologias();
		    	t7.setNome("7.png");
		    	t7.setTitulo("Gpl3");
		    	t7.setDescricao("software livre");
		    	
		    	tecnologias.add(t1);
		    	tecnologias.add(t2);
		    	tecnologias.add(t3);
		    	tecnologias.add(t4);
		    	tecnologias.add(t5);
		    	tecnologias.add(t6);
		    	tecnologias.add(t7);
		    	return tecnologias;
		    }
}

