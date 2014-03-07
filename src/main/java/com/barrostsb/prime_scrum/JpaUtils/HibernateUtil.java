package com.barrostsb.prime_scrum.JpaUtils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static final String HIBERNATE_SESSION = "hibernate_session";
	
	static{
		try {
			Configuration configuration = new Configuration().configure();
			
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao iniciar a session factory" + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}
