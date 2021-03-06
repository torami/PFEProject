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
	private static String actuators_fname;
	private static String spaces_fname;
	private static String openings_fname;
	private static String connectedobject_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;

	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.properties"));
			users_fname = prop.getProperty("users.filename");
			actuators_fname = prop.getProperty("actuators.filename");
			openings_fname = prop.getProperty("openings.filename");
			spaces_fname = prop.getProperty("spaces.filename");
			connectedobject_fname = prop.getProperty("connectedobjects.filename");
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
	serializeOpenings();
	serializeSpaces();
	serializeConnectedObject();
	serializeActuator();
	}
	public static void serializeOpenings() {
		File fopenings = new File(datarep_prefix + openings_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fopenings.renameTo(new File(backuprep_prefix + openings_fname+backup_suffix));
		try {
			fopenings.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.open, fopenings);
		System.out.print("Opening s�rialis�es");
		Server.open.print();		
	}

	public static void serializeActuator(){
		File factuators = new File(datarep_prefix + actuators_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		factuators.renameTo(new File(backuprep_prefix + actuators_fname+backup_suffix));
		try {
			factuators.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.actuator, factuators);
		System.out.print("Actuator s�rialis�es");
		Server.actuator.print();		
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
		System.out.print("Utilisateurs s�rialis�es");
		Server.uh.print();		
	}
	public static void serializeSpaces() {
		File fspaces = new File(datarep_prefix + spaces_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fspaces.renameTo(new File(backuprep_prefix + spaces_fname+backup_suffix));
		try {
			fspaces.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.space, fspaces);
		System.out.print("Space s�rialis�es");
		Server.space.print();		
	}
	public static void serializeConnectedObject() {
		File fconnectedobjects = new File(datarep_prefix + connectedobject_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fconnectedobjects.renameTo(new File(backuprep_prefix + connectedobject_fname+backup_suffix));
		try {
			fconnectedobjects.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.connected, fconnectedobjects);
		System.out.print("Connected Object s�rialis�s");
		Server.connected.print();		
	}

}