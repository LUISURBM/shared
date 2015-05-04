package sia.mm.cargue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import sia.mm.settings.Configuracion;
import sia.mm.settings.General;

/**
 * @author Luis
 * @version 1.0
 * @created 07-abr.-2013 7:45:29 p. m.
 */
public class Calendario {

	public Calendario() {

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param MENSAJES_HORARIOS
	 */
	public static Reporte[] crearReportes(final int MENSAJES_HORARIOS) {

		return null;
	}

	public static Vector<Reporte> getReportes() {
		Vector<Reporte> reportes = new Vector<Reporte>();

		int TOTAL_MENSAJES_POR_ACTIVIDAD = Integer.parseInt(General.obtenerPropiedad(General.CANTIDAD_REPORTES));

		if (Configuracion.ambienteDesarrollo()) {
			reportes = Formato.obtenerReportes(General.obtenerPropiedad(General.CONSULTAR_DESDE_CINTA));

			return reportes;

		} else {

			int i = 0;

			int hi = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);

			int hora = 0;

			do {

				Calendar linea_tiempo = GregorianCalendar.getInstance();

				hi = linea_tiempo.get(Calendar.HOUR_OF_DAY);

				i++;

				hora = hi - i;

				linea_tiempo.set(Calendar.HOUR_OF_DAY, hora);

				int m = linea_tiempo.get(Calendar.MONTH) + 1;

				int d = linea_tiempo.get(Calendar.DAY_OF_MONTH);

				String year = linea_tiempo.get(Calendar.YEAR) + "";

				int h = linea_tiempo.get(Calendar.HOUR_OF_DAY);
				
				String zuluTime = Formato.zulu(linea_tiempo);

				Reporte r = new Reporte(year, m, d, h, zuluTime);

				reportes.add(r);

				if (TOTAL_MENSAJES_POR_ACTIVIDAD <= i) {
					hora = 0;
				}

			} while (hora > 0);

			if (i < TOTAL_MENSAJES_POR_ACTIVIDAD) {

				int dia1 = GregorianCalendar.getInstance().get(
						Calendar.DAY_OF_MONTH);

				int j = 23;

				do {

					Calendar linea_tiempo = GregorianCalendar.getInstance();

					linea_tiempo.set(Calendar.DAY_OF_MONTH, dia1 - 1);

					linea_tiempo.set(Calendar.HOUR_OF_DAY, j);

					int m = linea_tiempo.get(Calendar.MONTH) + 1;

					int d = linea_tiempo.get(Calendar.DAY_OF_MONTH);

					String year = linea_tiempo.get(Calendar.YEAR) + "";

					int h = linea_tiempo.get(Calendar.HOUR_OF_DAY);
					
					String zuluTime = Formato.zulu(linea_tiempo);

					Reporte r = new Reporte(year, m, d, h, zuluTime);

					reportes.add(r);

					j--;

				} while (j >= 0);

				j = 23;

				do {

					Calendar linea_tiempo = GregorianCalendar.getInstance();

					linea_tiempo.set(Calendar.DAY_OF_MONTH, dia1 - 2);

					linea_tiempo.set(Calendar.HOUR_OF_DAY, j);

					int m = linea_tiempo.get(Calendar.MONTH) + 1;

					int d = linea_tiempo.get(Calendar.DAY_OF_MONTH);

					String year = linea_tiempo.get(Calendar.YEAR) + "";

					int h = linea_tiempo.get(Calendar.HOUR_OF_DAY);
					
					String zuluTime = Formato.zulu(linea_tiempo);

					Reporte r = new Reporte(year, m, d, h, zuluTime);

					reportes.add(r);

					j--;

				} while (j >= 0);

				int l = 23;

				do {

					Calendar linea_tiempo = GregorianCalendar.getInstance();

					linea_tiempo.set(Calendar.DAY_OF_MONTH, dia1 - 3);

					linea_tiempo.set(Calendar.HOUR_OF_DAY, l);

					int m = linea_tiempo.get(Calendar.MONTH) + 1;

					int d = linea_tiempo.get(Calendar.DAY_OF_MONTH);

					String year = linea_tiempo.get(Calendar.YEAR) + "";

					int h = linea_tiempo.get(Calendar.HOUR_OF_DAY);

					String zuluTime = Formato.zulu(linea_tiempo);
					
					Reporte r = new Reporte(year, m, d, h, zuluTime);

					reportes.add(r);

					l--;

				} while (l >= hi);

			}
			return reportes;
		}

	}

}// end Calendario