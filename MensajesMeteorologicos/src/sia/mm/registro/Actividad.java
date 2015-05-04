package sia.mm.registro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Actividad {

	public static final String QUERY = "query_ejecutado";

	public static final String EVENTOS = "eventos";

	public static void logEvento(String contenido, String url) {
		

			File archivo = null;
			FileWriter fr = null;
			BufferedWriter br = null;

			try {

				archivo = new File(url);
				fr = new FileWriter(archivo);
				br = new BufferedWriter(fr);

				for (int g = 0; g < contenido.length(); g++) {
					if (contenido.charAt(g) == ('\n')) {
						br.newLine();
					} else {
						br.write(contenido.charAt(g));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (null != br) {
						br.close();
					}

				} catch (Exception e2) {
					// e2.printStackTrace();
				}
				try {
					if (null != fr) {
						fr.close();
					}
				} catch (Exception e2) {
					// e2.printStackTrace();
				}
			}

		
}
	
	


}
