package sia.mm.cargue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import sia.mm.cargue.main.Standalone;
import sia.mm.registro.Actividad;
import sia.mm.settings.Almacen;
import sia.mm.settings.Estacion;
import sia.mm.settings.General;

/**
 * @author Luis
 * @version 1.0
 * @created 07-abr.-2013 7:45:33 p. m.
 */
public class Reporte {

	private String AÑO;
	private int MES;
	private int DIA;
	private int HORA;
	private String ZULUTIME;
	private String PATH_TO_FILE;
	private String SQL_STATEMENT;
	private String DATA;

	public Reporte(String año, int mes, int dia, int hora, String zuluTime) {

		this.AÑO = año;
		this.MES = mes;
		this.DIA = dia;
		this.HORA = hora;
		this.PATH_TO_FILE = General.determinarRuta() + ""
				+ (DIA < 10 ? ("0" + DIA) : String.valueOf(DIA)) + ""
				+ (MES < 10 ? ("0" + MES) : String.valueOf(MES)) + "" + AÑO
				+ "" + (HORA < 10 ? ("0" + HORA) : String.valueOf(HORA))
				+ "00.dat";
		this.ZULUTIME = zuluTime;

	}

	public String getNombreArchivo() {
		return this.PATH_TO_FILE;
	}

	public String getZuluTime() {
		return this.ZULUTIME;
	}

	public void transferir() {

		String configFile = General.determinarRuta() + "log.txt";

		Actividad.logEvento("Para " + this.getNombreArchivo() + "\nSQL: "
				+ this.obtenerSentenciaSql() + "\n", configFile);

		this.DATA = BD.consultar(this.obtenerSentenciaSql(), 16);

		String reporteHorario = Formato.formatoData(this.getZuluTime(),
				this.DATA);

		this.construirMensajeHorario(reporteHorario);

		this.transferir_Mensaje_Horario();

	}

	public String obtenerFecha() {
		return "'" + (DIA < 10 ? ("0" + DIA) : String.valueOf(DIA))
				+ (MES < 10 ? ("0" + MES) : String.valueOf(MES)) + AÑO + "'";
	}

	public String obtenerHora() {
		return "'" + (HORA < 10 ? ("0" + HORA) : String.valueOf(HORA))
				+ "0000' AND '"
				+ (HORA < 10 ? ("0" + HORA) : String.valueOf(HORA)) + "5959'";
	}

	/**
	 * 
	 * @param SENTENCIA
	 *            SQL
	 */
	public void asignarSentenciaSql(String SENTENCIA_SQL) {
		this.SQL_STATEMENT = SENTENCIA_SQL;
	}

	public String obtenerSentenciaSql() {

		String query = "SELECT ";

		String formato = Estacion.obtenerPropiedad(Estacion.DATO);

		String altitud = Estacion.obtenerPropiedad(Estacion.ALTITUD);
		String codigo = Estacion.obtenerPropiedad(Estacion.CODIGO);
		String consecutivo = Estacion.obtenerPropiedad(Estacion.CONSECUTIVO);
		String latitud = Formato.evitarExponentes(Estacion
				.obtenerPropiedad(Estacion.LATITUD));
		String longitud = Formato.evitarExponentes(Estacion
				.obtenerPropiedad(Estacion.LONGITUD));
		String nombre = Estacion.obtenerPropiedad(Estacion.NOMBRE);
		String plataforma = Estacion.obtenerPropiedad(Estacion.PLATAFORMA);

		String dviento = Formato.evitarExponentes(Formato.reemplazaPunto(
				formato, Estacion.obtenerPropiedad(Estacion.DVIENTO)));
		String hr = Formato.evitarExponentes(Formato.reemplazaPunto(formato,
				Estacion.obtenerPropiedad(Estacion.HUMEDAD_RELATIVA)));

		String precipitacion = Formato.evitarExponentes(Formato.reemplazaPunto(
				formato, Estacion.obtenerPropiedad(Estacion.PRECIPITACION)));
		String presion = Formato.evitarExponentes(Formato.reemplazaPunto(
				formato, Estacion.obtenerPropiedad(Estacion.PRESION)));

		String slp = Formato.evitarExponentes(Formato.reemplazaPunto(formato,
				Estacion.obtenerPropiedad(Estacion.SLP)));

		String temperatura = Formato.evitarExponentes(Formato.reemplazaPunto(
				formato, Estacion.obtenerPropiedad(Estacion.TEMPTERATURA)));
		String tmax = Formato.evitarExponentes(Formato.reemplazaPunto(formato,
				Estacion.obtenerPropiedad(Estacion.TMAX)));
		String tmin = Formato.evitarExponentes(Formato.reemplazaPunto(formato,
				Estacion.obtenerPropiedad(Estacion.TMIN)));
		String vviento = Formato.evitarExponentes(Formato.reemplazaPunto(
				formato, Estacion.obtenerPropiedad(Estacion.VVIENTO)));

		query += altitud + ", " + codigo + ", " + consecutivo + ", " + latitud
				+ ", " + longitud + ", " + nombre + ", " + plataforma + ", "
				+ dviento + ", " + hr + ", " + precipitacion + ", " + presion
				+ ", " + slp + ", " + temperatura + ", " + tmax + ", " + tmin
				+ ", " + vviento + " ";

		query += Estacion.obtenerPropiedad(Estacion.FROM) + " ";

		query += this.obtenerFechaParaConsulta() + " ";

		// query += Estacion.getInstance().obtenerPropiedad(Estacion.BETWEEN)
		// + " ";

		// query += this.obtenerHora() + " ";

		query += Estacion.obtenerPropiedad(Estacion.GROUP);

		/*
		 * registroEstacion= latitud + longitud + id_estacion + nombre +
		 * plataforma + fuente + VALID_ERROR_WARNING + consecutivo +
		 * DUPLICADOS_JULIAN + fecha + slp + QC1_4 + presion + QC5 +
		 * precipitacion + QC6 + tmax + QC7 + tmin + QC8_12_SALTOLINEA +
		 * presion_observacion + QC13 + altura + QC14 + temperatura + QC15_16 +
		 * vviento + QC17 + dviento + QC18_20 + hr + QC21_22 + linea3_4;
		 */

		this.asignarSentenciaSql(query);

		return this.SQL_STATEMENT;
	}

	private String obtenerFechaParaConsulta() {
		String r = " >= to_date('" + DIA + "-" + MES + "-" + AÑO + " " + HORA
				+ ":00:00','dd-mm-yyyy hh24:mi:ss') and fecha <= to_date('"
				+ DIA + "-" + MES + "-" + AÑO + " " + HORA
				+ ":59:59','dd-mm-yyyy hh24:mi:ss')";
		return r;
	}

	/**
	 * 
	 * @param REPORTE_HORA
	 */
	private void construirMensajeHorario(String REPORTE_HORA) {

		File archivoSalida = null;

		try {

			BufferedWriter bufferedWriter = null;

			archivoSalida = new File(this.getNombreArchivo());

			bufferedWriter = new BufferedWriter(new FileWriter(archivoSalida));

			bufferedWriter.write(REPORTE_HORA);

			try {

				if (bufferedWriter != null) {

					bufferedWriter.flush();

					bufferedWriter.close();

				}

			} catch (IOException ex) {

				Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,
						ex.getMessage());

			}

		} catch (IOException ex) {

			Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,
					ex.getMessage());

		}
	}

	private void transferir_Mensaje_Horario() {

		boolean transferido = false;

		boolean debug = false;// Prompt de Bart

		File a = new File(this.getNombreArchivo());
		try {

			if (a.exists()) {

				Socket socket = new Socket(
						Almacen.obtenerPropiedad(Almacen.URL), 21);
				BufferedWriter bufferedWriter = new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				bufferedWriter.write("USER "
						+ Almacen.obtenerPropiedad(Almacen.USUARIO) + "\r\n");
				bufferedWriter.flush();
				bufferedWriter.write("PASS "
						+ Almacen.obtenerPropiedad(Almacen.CLAVE) + "\r\n");
				bufferedWriter.flush();
				bufferedWriter
						.write("CWD "
								+ Almacen.obtenerPropiedad(Almacen.DIRECTORIO)
								+ "\r\n");
				bufferedWriter.flush();
				bufferedWriter.write("TYPE A\r\n");
				bufferedWriter.flush();
				bufferedWriter.write("PASV\r\n");
				bufferedWriter.flush();
				String response = null;

				while ((response = bufferedReader.readLine()) != null) {
					if (debug) {

						Logger.getLogger(Reporte.class.getName()).log(
								Level.INFO, "{0}", response);

					}
					if (response.startsWith("530")) {

						Logger.getLogger(Reporte.class.getName()).log(
								Level.SEVERE,
								"Login aunthentication failed." + "\n");

						break;
					}
					if (response.startsWith("227")) {
						String address = null;
						int port = -1;
						int opening = response.indexOf('(');
						int closing = response.indexOf(')', opening + 1);
						if (closing > 0) {
							String dataLink = response.substring(opening + 1,
									closing);
							StringTokenizer tokenizer = new StringTokenizer(
									dataLink, ",");
							try {
								address = tokenizer.nextToken() + "."
										+ tokenizer.nextToken() + "."
										+ tokenizer.nextToken() + "."
										+ tokenizer.nextToken();
								port = Integer.parseInt(tokenizer.nextToken())
										* 256
										+ Integer.parseInt(tokenizer
												.nextToken());
							} catch (Exception e) {
								System.out
										.println("Reporte :: transferir_Mensaje_Horario :: Exception port");
							}
							try {
								Socket transfer = new Socket(address, port);
								bufferedWriter.write("STOR " + a.getName()
										+ "\r\n");
								bufferedWriter.flush();
								response = bufferedReader.readLine();
								if (debug) {

									Logger.getLogger(Reporte.class.getName())
											.log(Level.INFO, "{0}\n", response);

								}
								if (response.startsWith("150")) {
									FileInputStream fileInputStream = new FileInputStream(
											this.getNombreArchivo());
									final int BUFFER_SIZE = 1024;
									byte buffer[] = new byte[BUFFER_SIZE];
									int len = 0;
									int off = 0;
									if (debug) {
										Logger.getLogger(
												Reporte.class.getName())
												.log(Level.INFO,
														"Almacén ''{0}'' \n",
														Almacen.obtenerPropiedad(Almacen.URL));
									}
									while ((len = fileInputStream.read(buffer)) != -1) {
										transfer.getOutputStream().write(
												buffer, off, len);
									}
									transfer.getOutputStream().flush();
									transfer.close();
									bufferedWriter.write("QUIT\r\n");
									bufferedWriter.flush();
									bufferedReader.close();
									socket.close();
									fileInputStream.close();

									transferido = true;

									break;
								}
							} catch (Exception e) {

								System.out
										.println("Reporte :: transferir_Mensaje_Horario :: Exception '"
												+ e.toString() + "'");

							}
						}
					}
				}
			} else {

				Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,
						"{0}no exist!", this.getNombreArchivo());

			}
			System.out.println("3");
		} catch (MalformedURLException e) {

			Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,
					e.getMessage());

			System.exit(0);

		} catch (IOException e) {

			Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,
					e.getMessage());

		}
		System.out.println("4");
		if (transferido) {
			try {

				String configFile = General.determinarRuta() + "log.txt";

				String tamaño = a.length() + " bytes.";

				boolean eliminado = a.delete();

				Actividad.logEvento(
						"\n\t * Fin Transferencia de Mensaje: "
								+ a.getAbsolutePath() + ";\n\t Tamaño : "
								+ tamaño + "\t Eliminado: " + eliminado,
						configFile);

				String msg = "\r\n" + a.getAbsolutePath() + "\n\t Trasferido: "
						+ transferido + ". Eliminado: " + eliminado
						+ ". Tamaño: " + tamaño + "\r\n";

				System.out.println(msg);

			} catch (Exception e) {
				System.out.println("Ex3");
				Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE,
						"No Se elimina archivo " + a.getAbsolutePath());

			}
		}

	}

	public void setDATA(String dATA) {
		DATA = dATA;
	}

	public void setPATH_TO_FILE(String pATH_TO_FILE) {
		PATH_TO_FILE = pATH_TO_FILE;
	}

	/*
	 * Eliminar ultimo que FTP SERVER identifica el archivo mediante el
	 * contenido de eliminar_ultimo.txt
	 */
	public void eliminar() {
		String contenido = Formato.nombreArchivo(this.getNombreArchivo());
		this.setPATH_TO_FILE(General.determinarRuta()
				+ Standalone.ELIMINAR_ULTIMO);
		this.construirMensajeHorario(contenido);
		this.transferir_Mensaje_Horario();
	}

}// end Reporte