package Context.server;

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
	private static String users_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;

	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.properties"));
			users_fname = prop.getProperty("users.filename");
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
	serializeUsers();
	}
	


	public static void serializeUsers() {
		File fusers = new File(datarep_prefix + users_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fusers.renameTo(new File(backuprep_prefix + users_fname+backup_suffix));
		try {
			fusers.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.uh, fusers);
		System.out.print("Utilisateurs sérialisés");
		Server.uh.print();		
	}
	

}