package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
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
public class BurndownController extends TarefaController implements Serializable{

	private static final long serialVersionUID = 1L;

	private CartesianChartModel burndown;  

	public BurndownController() {  
		createBurnDownChart();  
	}  

	public CartesianChartModel getBurnDownChart() {  
		return burndown;  
	}
	

	private void createBurnDownChart() {  
		burndown = new CartesianChartModel(); 

		burndown.addSeries(getProgressoIdeal());  
		burndown.addSeries(getProgressoAtual());  
	}
	
	private LineChartSeries getProgressoIdeal() {
		float tempoProjeto = getTempoProjeto();
		LineChartSeries progressoIdeal = new LineChartSeries();
		progressoIdeal.setLabel("Progresso Ideal");
		
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime( new Date() );

		while(tempoProjeto >= 0){
			progressoIdeal.set(calendar.getTime(), tempoProjeto);
			calendar.add( Calendar.DAY_OF_MONTH , 1 );  
			tempoProjeto -= 8;			
		}
		return progressoIdeal;
	}

	private float getTempoProjeto() {
		float tempoProjeto = 0;
		for(Tarefa tarefa : getTarefasDone()) {
			tempoProjeto += tarefa.getTempo_execucao();
	    }
		return tempoProjeto;
	}  

	private LineChartSeries getProgressoAtual() {
		LineChartSeries progressoAtual = new LineChartSeries();  
		progressoAtual.setLabel("Progresso Atual");  
		progressoAtual.setMarkerStyle("diamond");  

		progressoAtual.set(1, 10);  
		progressoAtual.set(2, 10);  
		progressoAtual.set(3, 10);  
		progressoAtual.set(4, 10);  
		progressoAtual.set(5, 10);
		return progressoAtual;
	}
	


}