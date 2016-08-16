package Context.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import Context.Model.Handler.ConnectedObjectHandler;
import Context.Model.Handler.OpeningHandler;
import Context.Model.Handler.SpaceHandler;
import Context.Model.Handler.UserHandler;
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
	private static String users_fname;
	private static String spaces_fname;
	private static String openings_fname;
	private static String connectedobjects_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;

	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.properties"));
			users_fname = prop.getProperty("users.filename");
			openings_fname = prop.getProperty("openings.filename");
			spaces_fname = prop.getProperty("spaces.filename");
			connectedobjects_fname = prop.getProperty("connectedobjects.filename");
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
			loadUsers();
			loadSpaces();
			loadConnectedObjects();
			loadOpenings();
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
			restoreUsers();
			restoreOpenings();
			restoreSpaces();
			restoreConnectedObjects();
			load();
		} catch (NoBackupFileException e) {
			e.printStackTrace();
		}
	}

	public static void loadUsers() throws LoadFileException {
		try {
			File fusers = new File(datarep_prefix + users_fname);
			Server.uh =  JAXB.unmarshal(fusers, UserHandler.class);
			System.out.print(fusers.getAbsolutePath());
			Server.uh.print();
		} catch (DataBindingException e) {
			throw new LoadFileException(
					"Erreur au chargement du fichier des utilisateurs");
		}
	}
	public static void loadOpenings() throws LoadFileException {
		try {
			File fopenings = new File(datarep_prefix + openings_fname);
			Server.open =  JAXB.unmarshal(fopenings, OpeningHandler.class);
			System.out.print(fopenings.getAbsolutePath());
			Server.open.print();
		} catch (DataBindingException e) {
			throw new LoadFileException(
					"Erreur au chargement du fichier des utilisateurs");
		}
	}
	public static void loadSpaces() throws LoadFileException {
		try {
			File fspaces = new File(datarep_prefix + spaces_fname);
			Server.space =  JAXB.unmarshal(fspaces, SpaceHandler.class);
			System.out.print(fspaces.getAbsolutePath());
			Server.space.print();
		} catch (DataBindingException e) {
			throw new LoadFileException(
					"Erreur au chargement du fichier des espaces");
		}
	}
	public static void loadConnectedObjects() throws LoadFileException {
		try {
			File fconnectedobjects = new File(datarep_prefix + connectedobjects_fname);
			Server.connected =  JAXB.unmarshal(fconnectedobjects, ConnectedObjectHandler.class);
			System.out.print(fconnectedobjects.getAbsolutePath());
			Server.connected.print();
		} catch (DataBindingException e) {
			throw new LoadFileException(
					"Erreur au chargement du fichier des objets connecté");
		}
	}


	public static void restoreUsers() throws NoBackupFileException {
		File fusers = new File(backuprep_prefix + users_fname + backup_suffix);
		if (fusers.exists())
			fusers.renameTo(new File(datarep_prefix + users_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été tourvé pour les utilisateurs");
	}
	public static void restoreOpenings() throws NoBackupFileException {
		File fopenings = new File(backuprep_prefix + openings_fname + backup_suffix);
		if (fopenings.exists())
			fopenings.renameTo(new File(datarep_prefix + openings_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été tourvé pour les ouvrants");
	}
	public static void restoreSpaces() throws NoBackupFileException {
		File fspaces = new File(backuprep_prefix + spaces_fname + backup_suffix);
		if (fspaces.exists())
			fspaces.renameTo(new File(datarep_prefix + spaces_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été tourvé pour les espaces");
	}
	public static void restoreConnectedObjects() throws NoBackupFileException {
		File fconnectedobjects = new File(backuprep_prefix + connectedobjects_fname + backup_suffix);
		if (fconnectedobjects.exists())
			fconnectedobjects.renameTo(new File(datarep_prefix + connectedobjects_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été trouvée pour les objets connectés");
	}
	


}