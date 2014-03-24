package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean (name = "taskBoardController")
public class TaskBoardController implements Serializable {  
  
	private static final long serialVersionUID = 1L;
	
	private DashboardModel model;  
      
    public TaskBoardController() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn();  
         
        column1.addWidget(0, "todo");
        column1.addWidget("sports");  
        column1.addWidget("finance");  
          
        column2.addWidget(0, "inprocess");
        column2.addWidget("lifestyle");  
        column2.addWidget("weather");  
        
        column3.addWidget(0, "done");
        column3.addWidget("politics");  
  
        model.addColumn(column1);  
        model.addColumn(column2);  
        model.addColumn(column3);  
        
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