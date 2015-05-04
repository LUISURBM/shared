package sia.mm.cargue.main;

import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import sia.mm.cargue.Calendario;
import sia.mm.cargue.Reporte;
import sia.mm.registro.Actividad;
import sia.mm.settings.Configuracion;
import sia.mm.settings.General;

public class Standalone implements Job {
	public static final String ELIMINAR_ULTIMO = "eliminar_ultimo.txt";
	private Vector<Reporte> mensajes;

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		try {

			String configFile = General.determinarRuta() + "log.txt";

			Configuracion.dato();

			String inicio = (new Date()) + "";

			int TOTAL_MENSAJES = Integer.parseInt(General
					.obtenerPropiedad(General.CANTIDAD_REPORTES));

			String msg = "\t\tActividad " + inicio + "\r\n" + "\n Se armaran "
					+ TOTAL_MENSAJES + " mensajes...\r\n";

			Actividad.logEvento(msg, configFile);

			System.out.println(msg);

			this.mensajes = Calendario.getReportes();

			for (int i = 0; i < TOTAL_MENSAJES
			// 2
			; i++) {

				String mensaje = "\n\tSe construye: "
						+ this.mensajes.elementAt(i).getNombreArchivo()
						+ " - Mensaje No. " + (i + 1) + " de " + TOTAL_MENSAJES;

				System.out.println(mensaje);

				this.mensajes.elementAt(i).transferir();

				Actividad.logEvento(mensaje, configFile);

			}

			System.out.println("Standalone :: eliminar_ultimo "
					+ this.mensajes.elementAt(TOTAL_MENSAJES - 1)
							.getNombreArchivo());

			this.mensajes.elementAt(TOTAL_MENSAJES - 1).eliminar();

			msg = "Fin actividad. Se enviaron " + this.mensajes.size()
					+ " mensajes." + "\nInicio actividad: " + inicio + " "
					+ "\nFin actividad: " + new Date() + ".";

			Actividad.logEvento(msg + "\n Fin actividad.\n", configFile);

			System.out.println(msg + "\n Espere siguiente ejecucion (60 min).");

		} catch (Exception e) {
			Logger.getLogger(Standalone.class.getName()).log(Level.INFO,
					"Problema en ejecución: " + e.getMessage());
		}

	}

}
