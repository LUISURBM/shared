package sia.mm.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
	private static Properties properties = null;

	/**
	 * Configuration file name
	 */
	public final static String CONFIG_FILE_NAME = "Oracle.properties";

	/**
	 * Clave acceso
	 */
	public static String CLAVE = "clave";

	/**
	 * Puerto Base Datos
	 */
	public static String PUERTO = "puerto";
	/**
	 * Recurso a consultar
	 */
	public static String RECURSO = "basedatos";
	/**
	 * IP Base Datos
	 */
	public static String URL_DB = "urldb";
	/**
	 * Usuario acceso
	 */
	public static String USUARIO = "usuario";

	private static void cargarCambios() {

		properties = new Properties();
		String rutaCompleta = General.determinarRuta() + CONFIG_FILE_NAME;
		InputStream inputStream = null;
		try {

			inputStream = new FileInputStream(rutaCompleta);

			properties.load(inputStream);

			BaseDatos.guardarCambios(properties);

			inputStream.close();

		} catch (IOException e) {
			Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE,
					e.getMessage());
			
		} catch (ExceptionInInitializerError e) {
			Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE,
					e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
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
		BaseDatos.cargarCambios();
		return properties.getProperty(key);
	}// getProperty

	public  static void actualizarPropiedad(String key, String valor) {
		BaseDatos.cargarCambios();
		properties.setProperty(key, valor);
		BaseDatos.guardarCambios(properties);
	}// setProperty

	private static void guardarCambios(Properties properties) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(General.determinarRuta()
					+ CONFIG_FILE_NAME);
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
