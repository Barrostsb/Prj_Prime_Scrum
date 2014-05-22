package com.barrostsb.prime_scrum.JpaUtils;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class ControlaCache implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();  
		  
        HttpServletResponse response = (HttpServletResponse) facesContext  
  
        .getExternalContext().getResponse();  
  
        response.addHeader("Pragma", "no-cache");  
  
        response.addHeader("Cache-Control", "no-cache");  
  
        response.addHeader("Cache-Control", "no-store");  
  
        response.addHeader("Cache-Control", "must-revalidate"); 
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

}
