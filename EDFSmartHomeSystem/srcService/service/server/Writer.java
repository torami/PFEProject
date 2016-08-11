package service.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.JAXB;

/**
 * Classe chargee de la serialisation des donnees.
 * @author ndelafor
 *
 */
public class Writer {
	private static String services_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;

	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.service.properties"));
			services_fname = prop.getProperty("services.filename");
			backup_suffix = prop.getProperty("backup.suffix");
			datarep_prefix = prop.getProperty("data.repository.prefix");
			backuprep_prefix = prop.getProperty("backup.repository.prefix");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Serialisation de toute la hierarchie d'objets
	 * Les anciens fichiers sont préservés pour une restauration en cas de probleme voir {@link Loader}.
	 * @throws IOException
	 */
	public static void serialize() throws IOException {
	serializeServices();
	}
	


	public static void serializeServices() {
		File fservices = new File(datarep_prefix + services_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fservices.renameTo(new File(backuprep_prefix + services_fname+backup_suffix));
		try {
			fservices.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.serh, fservices);
		System.out.print("Service serlialisees");
		Server.serh.print();		
	}
	

}