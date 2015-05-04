package sia.mm.settings;

import sia.mm.cargue.BD;

public class Configuracion {
	/**
	 * */

	public static void dato() {

		String segmento2 = Estacion.obtenerPropiedad(Estacion.DATO);
		String segmento1 = Estacion.obtenerPropiedad(Estacion.OTAD);

//		System.out.println("Dato :: " + "select "
//				+ segmento1.replace("dato", "80.0") + " from dual");

		String respuesta1 = BD.consultarDato("select "
				+ segmento1.replace("dato", "80.0") + " from dual");

//		System.out.println("Otad :: " + "select "
//				+ segmento2.replace("dato", "80.9") + " from dual");

		String respuesta2 = BD.consultarDato("select "
				+ segmento2.replace("dato", "80.9") + " from dual");

//		System.out.println(respuesta1 + "~" + respuesta2);
		if (respuesta1.contains("SQLGrammarException")) {
			System.out.println("DATO :: Usando '" + segmento2 + "'");

			Estacion.actualizarPropiedad(Estacion.DATO, segmento2);

			Estacion.actualizarPropiedad(Estacion.OTAD, segmento1);

		} else if (respuesta2.contains("SQLGrammarException")) {

			System.out.println("DATO :: Usando '" + segmento1 + "'");

			Estacion.actualizarPropiedad(Estacion.DATO, segmento1);

			Estacion.actualizarPropiedad(Estacion.OTAD, segmento2);
		}
	}

	public static boolean ambienteDesarrollo() {
		return General.obtenerPropiedad(General.AMBIENTE_DESARROLLO).contains(
				"true");
	}

}
