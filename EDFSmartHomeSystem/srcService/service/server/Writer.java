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
	private static String rules_fname;
	private static String activitys_fname;
	private static String modes_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;

	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.service.properties"));
			services_fname = prop.getProperty("services.filename");
			rules_fname = prop.getProperty("rules.filename");
			activitys_fname = prop.getProperty("activitys.filename");
			modes_fname = prop.getProperty("modes.filename");
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
	serializeActivitys();
	serializeRules();
	serializeModes();
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
		JAXB.marshal(ServerS.serh, fservices);
		System.out.print("Service serlialisees");
		ServerS.serh.print();		
	}

	public static void serializeRules() {
		File frules = new File(datarep_prefix + rules_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		frules.renameTo(new File(backuprep_prefix + rules_fname+backup_suffix));
		try {
			frules.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(ServerS.rule, frules);
		System.out.print("rule serlialisees");
		ServerS.rule.print();		
	}
	public static void 	serializeActivitys() {
		File factivitys = new File(datarep_prefix + activitys_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		factivitys .renameTo(new File(backuprep_prefix + activitys_fname+backup_suffix));
		try {
			factivitys .createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(ServerS.act, factivitys );
		System.out.print("Activit� serlialisees");
		ServerS.act.print();		
	}
	public static void 	serializeModes() {
		File fmodes = new File(datarep_prefix + modes_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fmodes .renameTo(new File(backuprep_prefix +modes_fname+backup_suffix));
		try {
			fmodes .createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(ServerS.mode,fmodes );
		System.out.print("Modes serlialisees");
		ServerS.act.print();		
	}

}