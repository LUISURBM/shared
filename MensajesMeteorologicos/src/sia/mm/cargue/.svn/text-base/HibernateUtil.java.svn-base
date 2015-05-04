package sia.mm.cargue;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import sia.mm.settings.General;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			// Logger.getLogger("org.hibernate").setLevel(Level.OFF);
			sessionFactory = new Configuration().configure(
					new File(General.determinarRuta() + "hibernate.cfg.xml"))
					.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}