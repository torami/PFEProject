package Context.Model.Handler;



import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import Context.server.Server;
import Context.server.Writer;
import Context.Model.ConnectedObject;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "connectedobjects")
public class ConnectedObjectHandler {
	@XmlElement(name = "connectedobject", required=true)
	private static List<ConnectedObject> connectedobject = new ArrayList<ConnectedObject>();
	/**
	 * Constructeur vide pour JAXB
	 */
	public ConnectedObjectHandler() {	}
	
	public void createConnectedObject(final String type,final String etat,final String emplacement){
		String connectedobjectid = ConnectedObject.createConnectedObjectIdFormemplacement(emplacement,type);
		if(getConnectedObjectFromId(connectedobjectid)==null) {
			connectedobject.add(new ConnectedObject(type,etat,emplacement));
			Writer.serializeConnectedObject();
		}
	}
	public ConnectedObject getConnectedObjectFromId(final String captid) {
		for(ConnectedObject cap : connectedobject){
			if(cap.getId().equals(captid)) return cap;
		}
		return null;
	}

	public void updateConnectedObjectFromId(final String oldconnectedobjectid, final String newconnectedobjectfinal, String type,final String etat,final String emplacement){
		ConnectedObject t = getConnectedObjectFromId(oldconnectedobjectid);
		t.setEmplacement(emplacement);
		t.setEtat(etat);
		t.setType(type);
		Writer.serializeConnectedObject();
	}
	/**
	 * Renvoie tous les {@link Capteur Capteur}
	 * @return une {@link List} de {@link capteur Capteurs}
	 */
	public List<ConnectedObject> getConnectedObject() {
		return connectedobject;
	}

	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */
	public void print(){
		System.out.print("=> "+connectedobject.size()+" Elements chargés \n");
	}
}
