package sia.mm.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OtrosDatos {
	private static Properties properties = new Properties();

	public final static String CONFIG_FILE_NAME = "OtrosDatos.properties";
	public static String FUENTE = "FUENTE";
	public static String VALID_ERROR_WARNING = "VALID_ERROR_WARNING";
	public static String DUPLICADOS_JULIAN = "DUPLICADOS_JULIAN";
	public static String QC1_4 = "QC1_4";
	public static String QC8_12_SALTOLINEA = "QC8_12_SALTOLINEA";
	public static String QC15_16 = "QC15_16";
	public static String QC18_20 = "QC18_20";
	public static String QC21_22 = "QC21_22";
	public static String LINEA3_4 = "LINEA3_4";

	/*
	 * registroEstacion= latitud + longitud + id_estacion + nombre + plataforma
	 * + fuente + elevacion + VALID_ERROR_WARNING + consecutivo +
	 * DUPLICADOS_JULIAN + fecha + slp + QC1_4 + presion + QC5 + precipitacion +
	 * QC6 + tmax + QC7 + tmin + QC8_12_SALTOLINEA + presion_observacion + QC13
	 * + altura + QC14 + temperatura + QC15_16 + vviento + QC17 + dviento +
	 * QC18_20 + hr + QC21_22 + linea3_4;
	 */

	private static  void cargarCambios() {

		properties = new Properties();
		String rutaCompleta = General.determinarRuta() + CONFIG_FILE_NAME;
		InputStream inputStream = null;
		try {

			inputStream = new FileInputStream(rutaCompleta);

			properties.load(inputStream);

		} catch (IOException e) {
			Logger.getLogger(OtrosDatos.class.getName()).log(Level.SEVERE,
					e.getMessage());
		} catch (ExceptionInInitializerError e) {
			Logger.getLogger(OtrosDatos.class.getName()).log(Level.SEVERE,
					e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}
	}

	

	/**
	 * Retorna la propiedad de configuración solicitada
	 * 
	 * @param key
	 *            dato a consultar en la confirguración.
	 * @return
	 */
	public static String obtenerPropiedad(String key) {
		OtrosDatos.cargarCambios();
		return properties.getProperty(key);
	}// getProperty

	public static void actualizarPropiedad(String key, String valor) {
		OtrosDatos.cargarCambios();
		properties.setProperty(key, valor);
		OtrosDatos.guardarCambios(properties);
	}// setProperty

	
	private static void guardarCambios(Properties properties) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(General.determinarRuta()
					+ CONFIG_FILE_NAME);
			properties.store(fos, "Se guardan cambios");
		} catch (FileNotFoundException e) {
			Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE,
					"Lectura Oracle.properties fallida." + e.getMessage());
		} catch (IOException e) {
			Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE,
					"Lectura de Oracle.properties fallida." + e.getMessage());
		}

		finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}

	}

}
