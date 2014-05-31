package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

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
		burndown.addSeries(getProgressoAtual());
	}
	

	private float getTempoProjeto() {
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
		
		Calendar calendar = Calendar.getInstance(); 
		
		
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
				
				calendar.setTime(tarefa.getDataTermino());
				progressoAtual.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoRestanteProjeto);
			}
		}
		else{
			progressoAtual.set("Inicio", getTempoProjeto());
		}
		return progressoAtual;
	}
}