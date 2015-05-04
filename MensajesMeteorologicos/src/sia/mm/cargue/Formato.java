package sia.mm.cargue;

import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import sia.mm.settings.Configuracion;
import sia.mm.settings.OtrosDatos;

/**
 * @author Luis
 * @version 1.0
 * @created 07-abr.-2013 7:45:32 p. m.
 */
public class Formato {

	private final static String QC = "      0";

	public static String A20FECHA(String dato) {
		String r = "";
		String espacios = "";
		if (dato.length() < 20) {
			int falta = 20 - dato.length();
			espacios = dameEspacios(falta, " ");
			r = espacios + dato;
		} else if (dato.length() > 20) {

			r = dato.substring(0, 10);
		}

		return r;
	}

	/**
	 * 
	 * @param dato
	 */
	public static String A40(String dato) {
		String r = "";
		String espacios = "";
		if (dato.length() < 40) {
			int falta = 40 - dato.length();
			espacios = dameEspacios(falta, " ");
			r = dato + espacios;
		} else if (dato.length() > 40) {

			r = dato.substring(0, 40);
		}

		return r;

	}

	/**
	 * 
	 * @param dato
	 */
	public static String A20(String dato) {
		String r = "";
		String espacios = "";
		if (dato.length() < 20) {
			int falta = 20 - dato.length();
			espacios = dameEspacios(falta, " ");
			r = dato + espacios;
		} else if (dato.length() > 20) {

			r = dato.substring(0, 20);
		}

		return r;
	}

	/**
	 * 
	 * @param dato
	 */
	public static String I10(String dato) {
		String r = "";
		String espacios = "";
		if (dato.length() < 10) {
			int falta = 10 - dato.length();
			espacios = dameEspacios(falta, " ");
			r = espacios + dato;
		} else if (dato.length() > 10) {

			r = dato.substring(0, 10);
		}

		return r;
	}

	/**
	 * 
	 * @param dato
	 */
	public static String I10Texto(String dato) {
		String d = (("         0").replace("0", dato));
		return d;

	}

	/**
	 * 
	 * @param dato
	 */
	public static String F13p5(String dato) {

		String r = "";
		String espacios = "";
		String decimales = "";
		String enteros = "";
		String ceros = "";

		int punto = dato.indexOf(".");
		int tamaño = dato.length();
		int negativo = dato.indexOf("-");

		if (tamaño <= 0 || dato.equals(".")) {
			return r = "-888888.00000";
		} else if (punto >= 0) {
			enteros = dato.substring(0, punto);

			decimales = dato.substring(punto, tamaño);

			tamaño = decimales.length();

			if (tamaño > 6) {
				decimales = decimales.substring(0, 6);
			} else {
				ceros += dameCeros(6 - tamaño);

			}

		} else {
			enteros = dato;
			decimales = ".00000";
		}
		if (negativo >= 0) {
			enteros = dameEntero(enteros, 7, true);

		} else {
			enteros = dameEntero(enteros, 7, false);
		}

		r = espacios + enteros + decimales + ceros;

		return r;
	}

	private static String dameEntero(String dato, int tope, boolean negativo) {
		if (dato.equals("-")) {
			dato = "-0";
		} else if (dato.equals("")) {
			dato = "0";
		}
		if (dato.length() > tope && negativo) {
			return "-9"
					+ dato.substring(dato.length() - (tope - 2), dato.length());
		} else if (dato.length() > tope && !negativo) {
			return "9"
					+ dato.substring(dato.length() - (tope - 1), dato.length());
		} else if (dato.length() < tope && negativo) {
			return dameEspacios(tope - dato.length(), " ") + dato;
		} else if (dato.length() < tope && !negativo) {
			return dameEspacios(tope - dato.length(), " ") + dato;
		}
		return "-" + dameEspacios(6, "8");
	}

	/**
	 * 
	 * @param dato
	 */
	public static String F20p5(String dato) {

		String r = "";

		String espacios = "";
		String decimales = "";
		String enteros = "";
		String ceros = "";

		int punto = dato.indexOf(".");
		int tamaño = dato.length();
		int negativo = dato.indexOf("-");

		if (tamaño <= 0) {
			return r = "-8888888888888.00000";
		}
		if (punto >= 0) {
			decimales = dato.substring(punto, tamaño);
			enteros = dato.substring(0, punto);
			tamaño = decimales.length();
			if (tamaño > 6) {
				decimales = decimales.substring(0, 6);
			} else {
				ceros += dameCeros(6 - tamaño);
			}

		} else {
			enteros = dato;
			decimales = ".00000";
		}
		if (negativo >= 0) {
			enteros = dameEntero(enteros, 14, true);
		} else {
			enteros = dameEntero(enteros, 14, false);
		}
		r = espacios + enteros + decimales + ceros;

		return r;

	}

	/**
	 * 
	 * @param dato
	 */
	public static String evaluarNulo(String dato) {
		if (dato.contains("0.00000")) {
			return "-888888.00000";
		} else {
			return dato;
		}
	}

	/**
	 * 
	 * @param FEHA_OBSERVACION
	 * @param DATA
	 */
	public static String formatoData(String FEHA_OBSERVACION, String DATA) {

		StringTokenizer token = new StringTokenizer(DATA, "<>");

		String reporteHora = "";

		while (token.hasMoreElements()) {

			String estacion = token.nextToken();

			String[] datosEstacion = estacion.split("~");

			reporteHora += Formato.reporteEstacion(datosEstacion,
					FEHA_OBSERVACION);

		}

		return reporteHora;
	}

	private static String reporteEstacion(String[] datosEstacion,
			String zuluTime) {

		String altitud = datosEstacion[0];

		String codigo = Formato.A40(datosEstacion[1]);

		String consecutivo = Formato.I10(datosEstacion[2]);

		String latitud = Formato.F20p5(datosEstacion[3]);

		String longitud = Formato.F20p5(datosEstacion[4]);

		String nombre = Formato.A40(datosEstacion[5]);

		String plataforma = Formato.A40(datosEstacion[6]);

		String dviento = Formato.F13p5(datosEstacion[7]);

		String hr = Formato.F13p5(datosEstacion[8]);

		String precipitacion = Formato.F13p5(datosEstacion[9]);

		String presion = Formato.evaluarNulo(Formato.F13p5(datosEstacion[10]));

		String slp = Formato.evaluarNulo(Formato.F13p5(datosEstacion[11]));

		String temperatura = Formato.F13p5(datosEstacion[12]);

		String tmax = Formato.F13p5(datosEstacion[13]);

		String tmin = Formato.F13p5(datosEstacion[14]);

		String vviento = Formato.F13p5(datosEstacion[15]);

		String reporteEstacion = latitud
				+ longitud
				+ codigo
				+ nombre
				+ plataforma
				+ Formato.A40(OtrosDatos.obtenerPropiedad(OtrosDatos.FUENTE))
				+ Formato.F20p5(altitud)
				+ Formato.I10Texto(OtrosDatos
						.obtenerPropiedad(OtrosDatos.VALID_ERROR_WARNING))
				+ consecutivo
				+ Formato.I10Texto(OtrosDatos
						.obtenerPropiedad(OtrosDatos.DUPLICADOS_JULIAN))
				+ zuluTime + slp + QC
				+ OtrosDatos.obtenerPropiedad(OtrosDatos.QC1_4) + presion + QC
				+ precipitacion + QC + tmax + QC + tmin + QC
				+ OtrosDatos.obtenerPropiedad(OtrosDatos.QC8_12_SALTOLINEA)
				+ presion + QC + Formato.F13p5(altitud) + QC + temperatura + QC
				+ OtrosDatos.obtenerPropiedad(OtrosDatos.QC15_16) + vviento
				+ QC + dviento + QC
				+ OtrosDatos.obtenerPropiedad(OtrosDatos.QC18_20) + hr + QC
				+ OtrosDatos.obtenerPropiedad(OtrosDatos.QC21_22)
				+ OtrosDatos.obtenerPropiedad(OtrosDatos.LINEA3_4) + "\r\n";

		return reporteEstacion;
	}

	public static String mensajeBienvenida() {
		String mensaje = "\n\t Standalone CargueModelos Ejecución inicial"
				+ "\n\t Configuracion .............. Ok (Archivos disponibles)"
				+ "\n\t Ambiente Desarrollo ........ "
				+ Configuracion.ambienteDesarrollo();
		return mensaje;
	}

	public static Vector<Reporte> obtenerReportes(String obtenerPropiedad) {
		// <021120121300.dat~20121102180000><021120121400.dat~20121102190000>
		String[] repor = obtenerPropiedad.split("<>");
		Reporte r;
		Vector<Reporte> reportes = new Vector<Reporte>();
		String name;
		for (int i = 0; i < repor.length; i++) {

			name = repor[i].split("~")[0].toString();

			// System.out.println(
			// (name.substring(1,3).toString()+"."+
			// Integer.parseInt(name.substring(3,5).toString())+"."+
			// Integer.parseInt(name.substring(5,9).toString())+"."+
			// Integer.parseInt(name.substring(9,13).toString())+"."+
			// repor[i].split("~")[1].toString().substring(0,14)));

			r = new Reporte(name.substring(5, 9).toString(),
					Integer.parseInt(name.substring(3, 5).toString()),
					Integer.parseInt(name.substring(1, 3).toString()),
					Integer.parseInt(name.substring(9, 11).toString()),
					repor[i].split("~")[1].toString().substring(0, 14));

			reportes.add(r);
		}
		return reportes;
	}

	public static String zulu(Calendar linea_tiempo) {

		linea_tiempo.set(Calendar.HOUR_OF_DAY,
				linea_tiempo.get(Calendar.HOUR_OF_DAY) + 5);

		int m = linea_tiempo.get(Calendar.MONTH) + 1;
		String mes = "" + (m < 10 ? "0" + m : m);

		int d = linea_tiempo.get(Calendar.DAY_OF_MONTH);
		String dia = "" + (d < 10 ? "0" + d : d);

		String year = linea_tiempo.get(Calendar.YEAR) + "";

		int h = linea_tiempo.get(Calendar.HOUR_OF_DAY);
		String hour = "" + (h < 10 ? "0" + h : h);

		return Formato.A20FECHA(year + mes + dia + hour + "0000");

	}

	private static String dameEspacios(int falta, String cadena) {
		String espacios = "";
		for (int i = 0; i < falta; i++) {
			espacios += cadena;
		}
		return espacios;
	}

	public static String evitarExponentes(String linea) {
		return "trunc(" + linea + ",5)";
	}

	public static String reemplazaPunto(String formato, String linea) {
		return linea.replace("DATO", formato);
	}

	private static String dameCeros(int falta) {
		String ceros = "";
		for (int i = 0; i < falta; i++) {
			ceros += "0";
		}
		return ceros;
	}

	public static String nombreArchivo(String path_to_file) {
		return path_to_file.substring(path_to_file.length() - 16,
				path_to_file.length());
	}
}// end Formato