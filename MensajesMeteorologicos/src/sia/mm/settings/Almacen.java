package sia.mm.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Almacen {

	private static Properties properties = null;

	/**
	 * Configuration file name
	 */
	public final static String CONFIG_FILE_NAME = "FtpServer.properties";

	/**
	 * Clave acceso
	 */
	public static String CLAVE = "clave";
	/**
	 * Directorio a almacenar
	 */
	public static String DIRECTORIO = "directorio";
	/**
	 * IP de Servidor FTP
	 */
	public static String URL = "url";
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

		} catch (IOException e) {
			Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE,
					e.getMessage());
		} catch (ExceptionInInitializerError e) {
			Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE,
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
		Almacen.cargarCambios();
		return properties.getProperty(key);
	}// getProperty

	public static void actualizarPropiedad(String key, String valor) {
		Almacen.cargarCambios();
		properties.setProperty(key, valor);
		Almacen.guardarCambios(properties);
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
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}

	}

}
