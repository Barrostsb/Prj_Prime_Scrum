package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "chartBean")
public class ChartBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private CartesianChartModel linearModel;  

	private CartesianChartModel categoryModel;  

	public ChartBean() {  
		createLinearModel();  
		createCategoryModel();  
	}  

	public CartesianChartModel getLinearModel() {  
		return linearModel;  
	}  

	public CartesianChartModel getCategoryModel() {  
		return categoryModel;  
	}  

	private void createLinearModel() {  
		linearModel = new CartesianChartModel();  

		LineChartSeries series1 = new LineChartSeries();  
		series1.setLabel("Series 1");  

		series1.set(1, 2);  
		series1.set(2, 1);  
		series1.set(3, 3);  
		series1.set(4, 6);  
		series1.set(5, 8);  

		LineChartSeries series2 = new LineChartSeries();  
		series2.setLabel("Series 2");  
		series2.setMarkerStyle("diamond");  

		series2.set(1, 6);  
		series2.set(2, 3);  
		series2.set(3, 2);  
		series2.set(4, 7);  
		series2.set(5, 9);  

		linearModel.addSeries(series1);  
		linearModel.addSeries(series2);  
	}  

	private void createCategoryModel() {  
		categoryModel = new CartesianChartModel();  

		ChartSeries boys = new ChartSeries();  
		boys.setLabel("Boys");  

		boys.set("2004", 120);  
		boys.set("2005", 100);  
		boys.set("2006", 44);  
		boys.set("2007", 150);  
		boys.set("2008", 25);  

		ChartSeries girls = new ChartSeries();  
		girls.setLabel("Girls");  

		girls.set("2004", 52);  
		girls.set("2005", 60);  
		girls.set("2006", 110);  
		girls.set("2007", 135);  
		girls.set("2008", 120);  

		categoryModel.addSeries(boys);  
		categoryModel.addSeries(girls);  
	}  
}  