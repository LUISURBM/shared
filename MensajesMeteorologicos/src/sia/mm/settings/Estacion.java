package sia.mm.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estacion {
	private static Properties properties = null;

	public final static String CONFIG_FILE_NAME = "EstacionesAutomaticas.properties";

	public static String DATO = "dato";
	public static String OTAD = "otad";
	public static String LATITUD = "latitud";
	public static String LONGITUD = "longitud";
	public static String CODIGO = "codigo";
	public static String NOMBRE = "nombre";
	public static String PLATAFORMA = "plataforma";
	public static String ALTITUD = "altitud";
	public static String CONSECUTIVO = "consecutivo";
	public static String SLP = "slp";
	public static String PRESION = "presion";
	public static String PRECIPITACION = "precipitacion";
	public static String TEMPTERATURA = "temperatura";
	public static String TMIN = "tmin";
	public static String TMAX = "tmax";
	public static String HUMEDAD_RELATIVA = "humedad_relativa";
	public static String DVIENTO = "dviento";
	public static String VVIENTO = "vviento";
	public static String FROM = "from";
	public static String FECHA = "fecha";
	public static String BETWEEN = "between";
	public static String HORA = "hora";
	public static String GROUP = "group";

	public static void cargarCambios() {
		properties = new Properties();

		String rutaCompleta = General.determinarRuta() + CONFIG_FILE_NAME;
		InputStream inputStream = null;
		try {

			inputStream = new FileInputStream(rutaCompleta);

			properties.load(inputStream);

			inputStream.close();

		} catch (IOException e) {
			Logger.getLogger(General.class.getName()).log(Level.SEVERE,
					e.getMessage());
		} catch (ExceptionInInitializerError e) {
			Logger.getLogger(General.class.getName()).log(Level.SEVERE,
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
		Estacion.cargarCambios();
		return properties.getProperty(key);
	}// getProperty

	public static void actualizarPropiedad(String key, String valor) {
		Estacion.cargarCambios();
		properties.setProperty(key, valor);
		Estacion.guardarCambios(properties);
	}// setProperty

	private static void guardarCambios(Properties properties) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(
					General.determinarRuta()
							+ CONFIG_FILE_NAME);
			properties.store(fos, "Se guardan cambios");
			fos.close();
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

/**
 * 
 * #Mon Feb 25 13:48:31 COT 2013 Formatos a datos LATITUD=trunc( dato , 5)
 * LONGITUD=trunc( dato , 5) formatoDato=TO_NUMBER(replace(DATO,'.',','))
 * 
 * latitud=siov.GRADOS_LATITUD+(siov.MINUTOS_LATITUD/60)+(siov.SEGUNDOS_LATITUD/
 * 3600)*decode(siov.DIRECCION_LATITUD,'N',1,'S',-1)
 * longitud=siov.GRADOS_LONGITUD
 * +(siov.MINUTOS_LONGITUD/60)+(siov.SEGUNDOS_LONGITUD
 * /3600))*decode(siov.DIRECCION_LONGITUD,'E',1,'W',-1)
 * CODIGO=siov.COD_INTERNO_ES NOMBRE=siov.NOMBRE_ES PLATAFORMA=siov.NOMBRE_PMA
 * ALTITUD=siov.ALTITUD CONSECUTIVO=rank() OVER (ORDER BY COD_INTERNO_ES)
 * FECHA=to_char((sysdate + 4/24), 'yyyymmddHH24')||'0000' SLP=AVG(CASE WHEN
 * SENSOR = 255 THEN DATO ELSE 0 END)*exp(siov.altitud*9.8)/(286.8*292.4))
 * PRESION=AVG(CASE WHEN SENSOR = 255 THEN DATO ELSE 0 END) AS PRESION SUM(CASE
 * WHEN SENSOR = 240 THEN DATO ELSE 0 END) TMAX=MAX(CASE WHEN SENSOR = 69 THEN
 * (DATO+273) ELSE 0 END) TMIN=MIN(CASE WHEN SENSOR = 70 THEN (DATO+273) ELSE 0
 * END) TEMPERATURA=MAX(CASE WHEN SENSOR = 68 THEN (DATO+273) ELSE 0 END)
 * VVIENTO=AVG(CASE WHEN SENSOR = 103 THEN DATO ELSE 0 END)
 * DVIENTO=STATS_MODE(CASE WHEN SENSOR = 104 THEN DATO ELSE 0 END) HR=AVG(CASE
 * WHEN SENSOR = 27 THEN DATO ELSE 0 END) FROM=FROM siov_estaciones siov,
 * saai_temporal saai WHERE saai.estacion = siov.cod_interno_es AND
 * saai.fecha='02112012' AND saai.hora BETWEEN '130000' AND '135959'
 * cinta=select to_char(sysdate - 1/24,'yyyymmddHH24') cinta from dual
 */
