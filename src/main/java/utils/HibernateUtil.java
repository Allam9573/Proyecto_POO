package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	public SessionFactory getSessionFactory() {
		SessionFactory sessionFactory;
		sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}

}
