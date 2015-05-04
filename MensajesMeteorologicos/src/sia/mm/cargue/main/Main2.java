package sia.mm.cargue.main;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class Main2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("bart.ideam.gov.co", 21);
			String pathname = "hibernate.cfg.xml";
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			dos.writeUTF(pathname);
			FileInputStream fis = new FileInputStream(pathname);
			int res = IOUtils.copy(fis, dos);
			fis.close();
			dos.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
