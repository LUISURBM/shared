package sia.mm.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class General {
	/**
	 * }
	 * 
	 * @author lfum
	 * 
	 */

	private static Properties properties = null;

	/**
	 * Configuration file name
	 */
	public final static String CONFIG_FILE_NAME = "General.properties";

	public static final String AMBIENTE_DESARROLLO = "ambienteDesarrollo";

	/**
	 * Ruta Temporal para escritura de archivo
	 */
	public static String RUTA_TEMPORAL_ARCHIVO_SALIDA = "rutaTemporalArchivoSalida";

	/*
	 * Para ambiente de desarrollo que no tiene datos de la última hora datos de
	 * prueba: consultarDesdeCinta=021120121300.dat~20121102180000
	 */
	public static String CONSULTAR_DESDE_CINTA = "consultarDesdeCinta";

	public static String CANTIDAD_REPORTES = "cantidadReportes";

	public static void cargarCambios() {

		properties = new Properties();
		String rutaCompleta = General.determinarRuta() + CONFIG_FILE_NAME;
		InputStream inputStream = null;
		try {

			inputStream = new FileInputStream(rutaCompleta);

			properties.load(inputStream);

			properties.setProperty(RUTA_TEMPORAL_ARCHIVO_SALIDA,
					General.determinarRuta());

			inputStream.close();

			General.guardarCambios(properties);

		} catch (IOException e) {
			Logger.getLogger(General.class.getName()).log(Level.SEVERE,
					e.getMessage());
		} catch (ExceptionInInitializerError e) {
			Logger.getLogger(General.class.getName()).log(Level.SEVERE,
					e.getMessage());
		}

		finally {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}
	}

	private static void guardarCambios(Properties properties) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(
					properties
							.getProperty(General.RUTA_TEMPORAL_ARCHIVO_SALIDA)
							+ General.CONFIG_FILE_NAME);
			properties.store(fos, "Se guardan cambios");
			fos.close();
		} catch (FileNotFoundException e) {
			Logger.getLogger(General.class.getName()).log(Level.SEVERE,
					"Lectura General.properties fallida." + e.getMessage());
		} catch (IOException e) {
			Logger.getLogger(General.class.getName()).log(Level.SEVERE,
					"Lectura de General.properties fallida." + e.getMessage());
		} finally {
			try {
				fos.close();
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
		General.cargarCambios();
		return properties.getProperty(key);
	}// getProperty

	public static void actualizarPropiedad(String key, String valor) {
		General.cargarCambios();
		properties.setProperty(key, valor);
		General.guardarCambios(properties);
	}// setProperty

	public static String determinarRuta() {

		File archivoA = new File("Prueba.properties");

		String ruta1 = archivoA.getAbsolutePath();

		ruta1 = ruta1.substring(0, ruta1.indexOf("Prueba.properties")-1);

		ruta1 += "\\Config\\";

		archivoA.delete();

//		System.out.println("Ruta> " + ruta1);

		return ruta1;
	}

}
