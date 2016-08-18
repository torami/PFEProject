package service.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import service.model.ModeOperator;
import service.model.handlers.ActivityHandler;
import service.model.handlers.ModeOperatorHandler;
import service.model.handlers.ServiceHandler;
import Context.exceptions.LoadFileException;
import Context.exceptions.NoBackupFileException;


/**
 * Classe de chargement des donnees dans la structure objet du serveur. Les
 * fichiers lus ont ete generes par la classe {@link Writer}.
 * 
 * @author ndelafor
 * 
 */
public class Loader {
	private static String services_fname;
	private static String modes_fname;

	private static String activitys_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;

	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.service.properties"));
			services_fname = prop.getProperty("services.filename");
			modes_fname = prop.getProperty("modes.filename");
			activitys_fname= prop.getProperty("activitys.filename");
			backup_suffix = prop.getProperty("backup.suffix");
			datarep_prefix = prop.getProperty("data.repository.prefix");
			backuprep_prefix = prop.getProperty("backup.repository.prefix");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load() {
		try {
			loadservices();
			loadactivitys();
			loadmodes();


		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Un probleme est survenu pendant le chargement des donnees. \nLes fichiers de sauvegarde restaurees.");
//			restore();
		}
	}

	/**
	 * Restauration des fichiers de backup en cas de probleme (corruption de
	 * fichier etc)
	 */
	public static void restore() {
		try {
			restoreservices();
			restoreactivitys();
			restoremodes();
			load();
		} catch (NoBackupFileException e) {
			e.printStackTrace();
		}
	}

	public static void loadservices() throws LoadFileException {
		try {
			File fservices = new File(datarep_prefix + services_fname);
			ServerS.serh =   JAXB.unmarshal(fservices, ServiceHandler.class);
			System.out.print(fservices.getAbsolutePath());
			ServerS.serh.print();
		} catch (DataBindingException e) {
			throw new LoadFileException(
					"Erreur au chargement du fichier des utilisateurs");
		}
	}
	public static void loadmodes() throws LoadFileException {
		try {
			File fmodes = new File(datarep_prefix + modes_fname);
			ServerS.mode =   JAXB.unmarshal(fmodes, ModeOperatorHandler.class);
			System.out.print(fmodes.getAbsolutePath());
			ServerS.mode.print();
		} catch (DataBindingException e) {
			throw new LoadFileException(
					"Erreur au chargement du fichier des utilisateurs");
		}
	}
	public static void loadactivitys() throws LoadFileException {
		try {
			File factivitys = new File(datarep_prefix + activitys_fname);
			ServerS.act =   JAXB.unmarshal(factivitys , ActivityHandler.class);
			System.out.print(factivitys .getAbsolutePath());
			ServerS.act.print();
		} catch (DataBindingException e) {
			throw new LoadFileException(
					"Erreur au chargement du fichier des activit�s");
		}
	}


	public static void restoreservices() throws NoBackupFileException {
		File fservices = new File(backuprep_prefix + services_fname + backup_suffix);
		if (fservices.exists())
			fservices.renameTo(new File(datarep_prefix + services_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été trouvé pour les services");
	}
	public static void restoremodes() throws NoBackupFileException {
		File fmodes = new File(backuprep_prefix + modes_fname + backup_suffix);
		if (fmodes.exists())
			fmodes.renameTo(new File(datarep_prefix + modes_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été trouvé pour les modes");
	}

	public static void restoreactivitys() throws NoBackupFileException {
		File factivitys = new File(backuprep_prefix + activitys_fname + backup_suffix);
		if (factivitys.exists())
			factivitys.renameTo(new File(datarep_prefix + activitys_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été trouvé pour les activit�s");
	}


}