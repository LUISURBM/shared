package sia.mm.cargue;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;

public class BD {

	public static String consultar(String sql, int columns) {

		String r = "";
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.beginTransaction();
			List<Object[]> data = session.createSQLQuery(sql).list();
			ListIterator<Object[]> dataIt = data.listIterator();
			while (dataIt.hasNext()) {
				r += "<";
				for (int i = 0; i < columns; i++) {// Cantidad de columnas
													// devueltas por el Query
													// igual a cantidad de
													// campos para cada
													// registro, contando todos
													// los controles de calidad,
													// saltos de linea
					r += (((Object[]) (dataIt.next()))[i]).toString() + "~";
				}
				r += ">";

			}

			session.flush();
			session.getTransaction().rollback();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return r;
	}

	public static String consultarDato(String sql) {

		String r = "";
		try {

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();

			session.beginTransaction();

			Object data = session.createSQLQuery(sql).uniqueResult();

			r += "<";
			r += (BigDecimal) (data);
			r += ">";

			session.flush();

			session.getTransaction().rollback();

		} catch (Exception ex) {
			System.out.println("DATO EX :: '" + ex.toString() + "'");
			r = ex.toString();
		}

		return r;
	}

}
