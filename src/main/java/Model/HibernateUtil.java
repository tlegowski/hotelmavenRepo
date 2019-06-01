/*
package Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			if (sessionFactory == null) {
				synchronized (SessionFactory.class) {
					if (sessionFactory == null) {
						Configuration configuration = new Configuration().configure();
						StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
						registryBuilder.applySettings(configuration.getProperties());
						ServiceRegistry serviceRegistry = registryBuilder.build();
						SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

						Session session = sessionFactory.openSession();
					}
				}
			}
			return sessionFactory;
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
*/
