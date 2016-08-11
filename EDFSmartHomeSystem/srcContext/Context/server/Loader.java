package Context.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

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

	public static void load() {
		try {
			loadUsers();
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


	public static void restoreUsers() throws NoBackupFileException {
		File fusers = new File(backuprep_prefix + users_fname + backup_suffix);
		if (fusers.exists())
			fusers.renameTo(new File(datarep_prefix + users_fname));
		else
			throw new NoBackupFileException(
					"Aucun fichier de sauvegarde pour n'a été trouvé pour les utilisateurs");
	}

	


}