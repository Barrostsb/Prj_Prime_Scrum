package com.barrostsb.prime_scrum.controller;

import java.util.ArrayList;
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
	    	ScreenShots t1 = new ScreenShots();
	    	t1.setNome("1.png");
	    	t1.setTitulo("SCRUM TASK BOARD");
	    	t1.setDescricao("task");
	    	
	    	ScreenShots t2 = new ScreenShots();
	    	t2.setNome("2.png");
	    	t2.setTitulo("Eclipse IDE");
	    	t2.setDescricao("Eclipse IDE Kepler");
	    	
	    	ScreenShots t3 = new ScreenShots();
	    	t3.setNome("3.png");
	    	t3.setTitulo("JFS");
	    	t3.setDescricao("Java Server Faces");
	    	
//	    	Tecnologias t4 = new Tecnologias();
//	    	t4.setNome("4.png");
//	    	t4.setTitulo("Prime Faces");
//	    	t4.setDescricao("Implementação do jsf");
//	    	
//	    	Tecnologias t5 = new Tecnologias();
//	    	t5.setNome("5.png");
//	    	t5.setTitulo("Hibernate");
//	    	t5.setDescricao("hibernate ");
//	    	
//	    	Tecnologias t6 = new Tecnologias();
//	    	t1.setNome("6.png");
//	    	t1.setTitulo("Mysql");
//	    	t1.setDescricao("SGBD");
//	    	
//	    	Tecnologias t7 = new Tecnologias();
//	    	t1.setNome("7.png");
//	    	t1.setTitulo("Gpl3");
//	    	t1.setDescricao("software livre");
	    	
	    	images.add(t1);
	    	images.add(t2);
	    	images.add(t3);
//	    	tecnologias.add(t4);
//	    	tecnologias.add(t5);
//	    	tecnologias.add(t6);
//	    	tecnologias.add(t7);
	    	return images;
	    }
		
		 public List<Tecnologias> createTecnologias(){
		    	tecnologias = new ArrayList<Tecnologias>();
		    	Tecnologias t1 = new Tecnologias();
		    	t1.setNome("1.png");
		    	t1.setTitulo("Linguagem Java");
		    	t1.setDescricao("Linguagem Java 1.7");
		    	
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
		    	t1.setNome("6.png");
		    	t1.setTitulo("Mysql");
		    	t1.setDescricao("SGBD");
		    	
		    	Tecnologias t7 = new Tecnologias();
		    	t1.setNome("7.png");
		    	t1.setTitulo("Gpl3");
		    	t1.setDescricao("software livre");
		    	
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

