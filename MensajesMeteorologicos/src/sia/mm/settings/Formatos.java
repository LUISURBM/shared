package sia.mm.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Formatos {

	private static Properties properties = null;

	/**
	 * Configuration file name
	 */
	public final static String CONFIG_FILE_NAME = "Formatos.properties";

	/**
	 * Clave acceso
	 */
	public static String LATITUD = "latitud";
	public static String ID_ESTACION = "id_estacion";
	public static String NOMBRE = "nombre";
	public static String PLATAFORMA = "plataforma";
	public static String FUENTE = "fuente";
	public static String ELEVACION = "elevacion";
	public static String CONSECUTIVO = "consecutivo";
	public static String FECHA = "fecha";
	public static String SLP = "slp";
	public static String PRESION = "presion";
	public static String PRECIPITACION = "precipitacion";
	public static String TMAX = "tmax";
	public static String TMIN = "tmin";
	public static String PRESION_OBSERVACION = "presion_observacion";
	public static String ALTURA = "altura";
	public static String TEMPERATURA = "temperatura";
	public static String VVIENTO = "vviento";
	public static String DVIENTO = "dviento";
	public static String HR = "hr";

	private static void cargarCambios() {

		properties = new Properties();
		String rutaCompleta = General.determinarRuta() + CONFIG_FILE_NAME;
		InputStream inputStream = null;
		try {

			inputStream = new FileInputStream(rutaCompleta);

			properties.load(inputStream);

			inputStream.close();

			Formatos.guardarCambios(properties);

		} catch (IOException e) {
			Logger.getLogger(Formatos.class.getName()).log(Level.SEVERE,
					e.getMessage());
		} catch (ExceptionInInitializerError e) {
			Logger.getLogger(Formatos.class.getName()).log(Level.SEVERE,
					e.getMessage());
		}

		finally {
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
		Formatos.cargarCambios();
		return properties.getProperty(key);
	}// getProperty

	public static void actualizarPropiedad(String key, String valor) {
		Formatos.cargarCambios();
		properties.setProperty(key, valor);
		Formatos.guardarCambios(properties);
	}// setProperty

	private static void guardarCambios(Properties properties) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(General.determinarRuta()
					+ General.CONFIG_FILE_NAME);

			properties.store(fos, "Se guardan cambios");
			fos.close();
		} catch (FileNotFoundException e) {
			Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE,
					"Lectura Oracle.properties fallida." + e.getMessage());
		} catch (IOException e) {
			Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE,
					"Lectura de Oracle.properties fallida." + e.getMessage());
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}

	}

}
