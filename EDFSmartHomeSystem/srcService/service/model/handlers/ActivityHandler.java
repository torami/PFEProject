package service.model.handlers;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;





import Context.Model.ConnectedObject;
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
@XmlRootElement(name = "activitys")
public class ActivityHandler {
	@XmlElement(name = "activity", required=true)
	private static List<Activity> activitys = new ArrayList<Activity>();
	public ActivityHandler() {}
	/**
	 * creation of an activity.
	 * @param id identity of activity
	 * @param label name of the activity 
	 * @param <list> resource list of resources maintained by the activity
	 * @throws UnsupportedEncodingException
	 */
	public void createActivity(final String label,final List<ConnectedObject> resource) {
		try {
			activitys.add(new Activity(label,resource));
			Writer.serializeActivitys();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */	
	public void print(){
		System.out.print("=> "+activitys.size()+" element chargés \n");
	}
}
