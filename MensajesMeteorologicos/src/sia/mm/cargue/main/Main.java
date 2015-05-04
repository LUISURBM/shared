package sia.mm.cargue.main;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import sia.mm.cargue.Formato;
import sia.mm.registro.Actividad;
import sia.mm.settings.Configuracion;
import sia.mm.settings.General;

public class Main {

	private static Scheduler horario;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Main :: Configurar dato");
		
		Configuracion.dato();
		
		String configFile = General.determinarRuta()+"log.txt";

		Actividad.logEvento(Formato.mensajeBienvenida(),configFile);

		System.out.println("\t Standalone CargueModelos Ejecución inicial");

		try {
			Main.programar();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,
					"Problema en ejecución: " + e.getMessage());

		}

	}

	public static void programar() throws Exception, SchedulerException {

		try {

			SchedulerFactory factoria = new StdSchedulerFactory();

			horario = factoria.getScheduler();

			horario.start();

		} catch (Exception ex) {

			throw new Exception();

		}

		JobDetail job = new JobDetail("Alimentar Modelos Meteorologicos", null,
				Standalone.class);

		//CronTrigger trigger = new CronTrigger();

		// int hj = GregorianCalendar.getInstance().get(Calendar.MINUTE) + 1;

		// String horaEjecucion = "" + (hj < 10 ? "0" + hj : hj);
		//
		//trigger.setCronExpression("0 49 * * * ?");

		// System.out.println(horaEjecucion);

		Trigger trigger = TriggerUtils.makeMinutelyTrigger(
				2
//			60
				//
			);

		trigger.setName("Disparador horario");

		Date d = new Date();

		trigger.setStartTime(d);

		trigger.setName("DisparadorCadaHora");

		try {

			horario.scheduleJob(job, trigger);

		} catch (SchedulerException ex) {

			throw new SchedulerException();

		}

	}

}
