package service.server;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




import service.model.handlers.ServiceHandler;
import Context.Model.User;
import Context.Model.Handler.UserHandler;

import com.sun.grizzly.ControllerStateListener;
import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

/**
 * Classe principale. C'est la classe qui integre et qui lance le serveur Grizzly.
 * La javadoc du serveur Grizzly est disponible a l'adresse : https://grizzly.dev.java.net/nonav/apidocs/index.html
 * @author ndelafor
 */
public class ServerS {
	/**
	 * Gestionnaire de {@link User}
	 */

	static public ServiceHandler serh = new ServiceHandler();

	public static void main(String[] args) {
		Loader.load(); // Chargement des fichiers XML
		
		// definition de l'URL de base du serveur et du port a ecouter
		final String baseUri = "http://localhost:9997"+"/";

		// renseigne le moteur de Jersey sur l'emplacement des classes a deployer
		final Map<String, String> initParams = new HashMap<String, String>();
		initParams.put("com.sun.jersey.config.property.packages", "service.resources"); 
 

		try {
			// On deploie les ressource a baseURI et retourne un SelectorThread
			SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);

			// on lance le serveur
			ServerStateListener ssl = new ServerStateListener(); // Creation d'un listener pour suivre l'etat du serveur
			threadSelector.getController().addStateListener((ControllerStateListener) ssl); // Enregistrement du listener
			threadSelector.getController().start();
			
			// Simulation d'un shell pour le controle du serveur
			String cmd;
			Scanner in = new Scanner(System.in); // scanner de flux d'entree pour lire ce que tape l'utilisateur
			System.out.println("==> Starting grizzly...\n");
			System.out.print(String.format("WADL disponible à l'adresse : %sapplication.wadl", baseUri, baseUri));

			System.out.println("\nCommandes possibles => \"stop|quit|pause|resume\"");
			
			while(true){
				System.out.print("\nservershell>");
				cmd = in.nextLine();
				
				if(cmd.equals("pause")){
					System.out.println("==> Server paused...\n");
					threadSelector.getController().pause(); // pause du serveur
				}
				else if(cmd.equals("resume")){
					System.out.println("==> Server resume...\n");
					threadSelector.getController().resume(); // reprise du serveur
				}
				else if(cmd.equals("quit")||cmd.equals("stop")){
					threadSelector.getController().stop(); // arret du serveur
					System.out.println("Bye!\n"); // sortie de l'application
					break;
				}
			}
		threadSelector.stopEndpoint();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}