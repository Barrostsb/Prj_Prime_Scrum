package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.Calendar;
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
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.Tarefa;

@ManagedBean(name = "burndownController")
@ViewScoped

public class BurndownController extends TaskBoardController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int HORAS_DO_DIA = 8;
	private static final int PROJETO_CONCLUIDO = 0;

	private CartesianChartModel burndown;  

	public BurndownController() {  
		createBurnDownChart();  
	}  

	public CartesianChartModel getBurnDownChart() {  
		return burndown;  
	}
	

	private void createBurnDownChart() {  
		burndown = new CartesianChartModel(); 

		//burndown.addSeries(getProgressoIdeal());  
		burndown.addSeries(getProgressoAtual());  
	}
	
	private LineChartSeries getProgressoIdeal() {
		float tempoProjeto = getTempoProjeto();
		LineChartSeries progressoIdeal = new LineChartSeries();
		progressoIdeal.setLabel("Progresso Ideal");
		
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(new Date());
		
//		progressoIdeal.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoProjeto);
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
		float tempoProjeto = PROJETO_CONCLUIDO;
		for(Tarefa tarefa : getTarefaPorProjeto()) {
			tempoProjeto += tarefa.getTempo_execucao();
	    }
		return tempoProjeto;
	}  
	
	private LineChartSeries getProgressoAtual() {
		LineChartSeries progressoAtual = new LineChartSeries();  
		progressoAtual.setLabel("Progresso Atual");  
		progressoAtual.setMarkerStyle("diamond");  
		float tempoProjeto = getTempoProjeto();
		
		//TODO tornar dinamico com inicio do projeto
		Calendar calendar = Calendar.getInstance();  
		progressoAtual.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoProjeto);
		for(Tarefa tarefa : getTarefasDone()) {
			tempoProjeto -= tarefa.getTempo_execucao();
			
			//TODO ver com data de termino tornar dinamico os dois lines
			//TODO ORDENAR POR DATA
			calendar.setTime(tarefa.getDataTermino());
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR));
			progressoAtual.set(calendar.get(Calendar.DAY_OF_MONTH) +"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), tempoProjeto);
//			System.out.println("Progresso  " + tempoProjeto);
		}
		return progressoAtual;
	}
	


}