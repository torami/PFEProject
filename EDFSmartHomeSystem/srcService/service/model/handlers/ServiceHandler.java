package service.model.handlers;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Context.Model.User;
import service.server.Writer;
import service.model.Activity;
import service.model.Service;


/**
 * Gestion des instances de {@link Service}.
 * {@link ServiceHandler} implemente les methodes CRUD sur les {@link Service}.
 * @author TORKHANI Rami
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "services")
public class ServiceHandler {
	@XmlElement(name = "service", required=true)
	private static List<Service> services = new ArrayList<Service>();
	public ServiceHandler() {
	}
	/**
	 * création d'un nouveau service.
	 * @param subject le sujet du service
	 * @param active l'état du service 
	 * @param <list> activity list d'activitées offerte par le service
	 * @throws UnsupportedEncodingException
	 */
	public void createService(final String subject,final boolean active, final List<Activity> activity) {
		try {
			services.add(new Service(subject,active,activity));
			Writer.serializeServices();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Renvoie la liste de tous les service
	 * @return liste
	 */
	public List<Service> getServices() {
		return services;
	}
	/**
	 * Permet de récupérer l'instance d'un service à partir de son ID
	 * @param serviceid ID du service &agrave; r&eacute;cup&eacute;rer 
	 * @return service correspondant
	 */
	public Service getServiceFromId(final String serviceid) {
		for(Service u : services){
			if(u.getId().equals(serviceid)) return u;
		}
		return null;
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */	
	public void print(){
		System.out.print("=> "+services.size()+" element chargés \n");
	}
}
