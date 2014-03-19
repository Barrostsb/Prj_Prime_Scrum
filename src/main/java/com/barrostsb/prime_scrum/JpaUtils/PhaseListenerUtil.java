package com.barrostsb.prime_scrum.JpaUtils;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.hibernate.Session;

public class PhaseListenerUtil implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent phase) {
		System.out.println("Depois da fase: " + phase.getPhaseId());
		if (phase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
			Session session = FacesContextUtil.getRequestSession();
			try {
				session.getTransaction().commit();
			} catch (Exception e) {
				if (session.getTransaction().isActive()) {
					session.getTransaction().rollback();
				}
			} finally{
				session.close();
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent phase) {
		System.out.println("Antes da fase: " + phase.getPhaseId());
		if (phase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
			Session session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();
			FacesContextUtil.setRequestSession(session);
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
