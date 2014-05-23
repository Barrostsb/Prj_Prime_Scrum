package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.Tarefa;

@ManagedBean(name = "burndownController")
@ViewScoped

public class BurndownController extends TaskBoardController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int HORAS_DO_DIA = 8;
	private static final int PROJETO_CONCLUIDO = 0;
	private float tempoTotalProjeto;
	
	public float getTempoTotalProjeto() {
		return tempoTotalProjeto;
	}

	public void setTempoTotalProjeto(float tempoTotalProjeto) {
		this.tempoTotalProjeto = tempoTotalProjeto;
	}

	private float tempoRestanteProjeto;

	public float getTempoRestanteProjeto() {
		return tempoRestanteProjeto;
	}

	public void setTempoRestanteProjeto(float tempoRestanteProjeto) {
		this.tempoRestanteProjeto = tempoRestanteProjeto;
	}

	private CartesianChartModel burndown; 
	

	public BurndownController() {  
		createBurnDownChart();  
	}  

	public CartesianChartModel getBurnDownChart() {  
		return burndown;  
	}
	

	private void createBurnDownChart() {  
		burndown = new CartesianChartModel(); 
		
		
		
//		if(getTarefasDone().size() > 0 ){
//			burndown.addSeries(getProgressoAtual());  
//			burndown.addSeries(getProgressoIdeal());
//		}else{
//			burndown.addSeries(getProgressoIdeal());
//			burndown.addSeries(getProgressoAtual());
//		}
		burndown.addSeries(getProgressoAtual());
	}
	
	private LineChartSeries getProgressoIdeal() {
		float tempoProjeto = getTempoProjeto();
		LineChartSeries progressoIdeal = new LineChartSeries();
		progressoIdeal.setLabel("Progresso Ideal");
		
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(new Date());
		
		progressoIdeal.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoProjeto);
		while(tempoProjeto > 0){
			progressoIdeal.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoProjeto);
			tempoProjeto -= HORAS_DO_DIA;			
			calendar.add( Calendar.DAY_OF_MONTH , 1 ); 
		}
		if ((tempoProjeto <= 0) && (tempoProjeto > -8)){
			tempoProjeto = PROJETO_CONCLUIDO;
			progressoIdeal.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoProjeto);
		}
		
		return progressoIdeal;
	}

	private float getTempoProjeto() {
//		float tempoProjeto = PROJETO_CONCLUIDO;
		float tempoProjeto = 0;
		for(Tarefa tarefa : getTarefaPorProjeto()) {
			tempoProjeto += tarefa.getTempo_execucao();
	    }
		return tempoProjeto;
	}  
	
	private LineChartSeries getProgressoAtual() {
		LineChartSeries progressoAtual = new LineChartSeries();  
		progressoAtual.setLabel("Progresso Atual");  
		progressoAtual.setMarkerStyle("diamond");  
		tempoRestanteProjeto = getTempoProjeto();
		setTempoTotalProjeto(tempoRestanteProjeto);
		
		//TODO tornar dinamico com inicio do projeto
		Calendar calendar = Calendar.getInstance(); 
		
		
		//progressoAtual.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoProjeto);
		progressoAtual.set("Inicio", getTempoProjeto());
		if(getTarefasDone().size() > 0){
			Comparator comparator = new Comparator(){    
				public int compare(Object o1, Object o2) {
					Tarefa t1 = (Tarefa) o1;
					Tarefa t2 = (Tarefa) o2;
					return t1.getDataTermino().compareTo(t2.getDataTermino());
				}  
			}; 
			Collections.sort(getTarefasDone(), comparator); 
			
			
			for(Tarefa tarefa : getTarefasDone()) {
				tempoRestanteProjeto -= tarefa.getTempo_execucao();
				
				//TODO ver com data de termino tornar dinamico os dois lines
				//TODO ORDENAR POR DATA
				calendar.setTime(tarefa.getDataTermino());
	//			System.out.println(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR));
				progressoAtual.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoRestanteProjeto);
	//			System.out.println("Progresso  " + tempoProjeto);
			}
			
			
//			for (int aux = 0; aux < 5; aux ++){
//				calendar.add( Calendar.DAY_OF_MONTH , 1 );
//				progressoAtual.set(calendar.get(Calendar.DAY_OF_MONTH ) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), null);
//			}
			
			//TODO fim da lista
//			int iteratorAux = 0;

//
//			for(Calendar listaFim : listaDataIdeal.subList(iteratorAux, listaDataIdeal.size())) {
//				progressoAtual.set(listaFim.get(Calendar.DAY_OF_MONTH) +"/"+listaFim.get(Calendar.MONTH)+"/"+listaFim.get(Calendar.YEAR), null);
//			}
		}
		else{
			progressoAtual.set("Inicio", getTempoProjeto());
		}
		return progressoAtual;
	}
	


}